package com.anamarin.bitcoinpricesapp.presentation.ui

import android.os.Bundle
import android.os.Handler
import android.view.View
import javax.inject.Inject
import dagger.android.AndroidInjection
import com.anamarin.bitcoinpricesapp.R
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.core.utils.*
import com.anamarin.bitcoinpricesapp.core.utils.DateAxisValueFormatter
import com.anamarin.bitcoinpricesapp.presentation.viewmodels.MainViewModel
import com.anamarin.bitcoinpricesapp.core.utils.viewModelUtil.buildViewModel
import com.anamarin.bitcoinpricesapp.core.utils.viewModelUtil.AppViewModelFactory
import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: AppViewModelFactory
    private val viewModel: MainViewModel by lazy { buildViewModel<MainViewModel>(factory) }

    private val disposable: LifeDisposable = LifeDisposable(this)

    private lateinit var entity: BitcoinInfoEntity

    private var period: String = MONTHS_PERIOD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        getData(MONTHS_PERIOD)

        supportActionBar?.title = getString(R.string.toolbar_title)

        tabLayout.addOnTabSelectedListener(object : BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> period = MONTHS_PERIOD
                    1 -> period = YEAR_PERIOD
                }
                getData(period)
            }
        })

        viewModel.liveDataConnection.observe(this, Observer { value ->
            if (!value) "You are offline, this chart could be outdated".snack(this)
        })


    }

    override fun onResume() {
        super.onResume()
        viewModel.checkNetWorkStatus()

        updateBtn.setOnClickListener {
            getData(period)
        }
    }

    private fun getData(period: String) {
        progressBar.visibility = View.VISIBLE
        disposable add viewModel.getBitcoinInfo(1, period, CHART_NAME)
            .doOnTerminate {
                progressBar.visibility = View.INVISIBLE
            }
            .subscribeBy(
                onSuccess = {
                    progressBar.visibility = View.GONE
                    if (it is Success) {
                        entity = it.data
                        description.text = entity.description
                        generateChart(period)
                    } else {
                        "Something went wrong".snack(this)
                    }
                }, onError = {
                    "An error occurred".snack(this)
                }
            )
    }

    private fun generateChart(period: String) {
        val entries = ArrayList<Entry>()

        for (coordinate: BitcoinCoordinatesModel in entity.values) {
            entries.add(Entry((coordinate.x).toFloat(), coordinate.y.toFloat()))
        }

        val charDataSet = LineDataSet(entries, "")

        charDataSet.setDrawValues(false)
        charDataSet.setDrawFilled(false)
        charDataSet.lineWidth = 3f
        charDataSet.fillColor = R.color.colorPrimary
        charDataSet.fillAlpha = R.color.colorAccent
        charDataSet.axisDependency = YAxis.AxisDependency.LEFT

        lineChart.xAxis.labelRotationAngle = 0f
        lineChart.xAxis.valueFormatter = DateAxisValueFormatter(period)
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.xAxis.setDrawGridLines(false)

        lineChart.invalidate()
        lineChart.data = LineData(charDataSet)
        lineChart.description.text = ""

        lineChart.axisRight.isEnabled = false
        lineChart.axisLeft.isDrawBottomYLabelEntryEnabled

        lineChart.legend.isEnabled = false
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)

        lineChart.animateX(1400, Easing.EaseInExpo)

        Handler().postDelayed({ lineChart.invalidate() }, 200)

    }
}


