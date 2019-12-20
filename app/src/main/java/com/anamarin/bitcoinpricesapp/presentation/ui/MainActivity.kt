package com.anamarin.bitcoinpricesapp.presentation.ui

import android.os.Bundle
import android.os.Handler
import javax.inject.Inject
import dagger.android.AndroidInjection
import com.anamarin.bitcoinpricesapp.R
import androidx.appcompat.app.AppCompatActivity
import com.anamarin.bitcoinpricesapp.core.result.Success
import com.anamarin.bitcoinpricesapp.core.utils.*
import com.anamarin.bitcoinpricesapp.core.utils.DateAxisValueFormatter
import com.anamarin.bitcoinpricesapp.presentation.viewmodels.MainViewModel
import com.anamarin.bitcoinpricesapp.core.utils.viewModelUtil.buildViewModel
import com.anamarin.bitcoinpricesapp.core.utils.viewModelUtil.AppViewModelFactory
import com.anamarin.bitcoinpricesapp.data.models.BitcoinCoordinatesModel
import com.anamarin.bitcoinpricesapp.domain.entities.BitcoinInfoEntity
import com.github.mikephil.charting.animation.Easing
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        tabLayout.addOnTabSelectedListener(object : BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab) {
                var period = WEEK_PERIOD
                when (tab.position) {
                    0 -> period = WEEK_PERIOD
                    1 -> period = MONTHS_PERIOD
                    2 -> period = YEAR_PERIOD
                }
                getData(period)
            }
        })

    }

    override fun onResume() {
        super.onResume()

        getData(WEEK_PERIOD)
    }

    private fun getData(period: String) {
        viewModel.getBitcoinInfo(1, period, CHART_NAME)

//            .subscribeBy(
//                onSuccess = {
//                    if (it is Success) {
//                        entity = it.data
//                        generateChart()
//                    }
//                }, onError = {
//                    "There was an error".toast(this)
//                }
//            )

    }

    private fun generateChart() {
        val entries = ArrayList<Entry>()

        for (coordinate: BitcoinCoordinatesModel in entity.values) {
            entries.add(Entry((coordinate.x).toFloat(), coordinate.y.toFloat()))
        }

        val vl = LineDataSet(entries, null)

        vl.setDrawValues(false)
        vl.setDrawFilled(false)
        vl.lineWidth = 3f
        vl.fillColor = R.color.colorPrimary
        vl.fillAlpha = R.color.colorAccent

        lineChart.xAxis.labelRotationAngle = 0f
        lineChart.xAxis.valueFormatter = DateAxisValueFormatter()

        lineChart.data = LineData(vl)

        lineChart.axisRight.isEnabled = false
        lineChart.axisLeft.isDrawBottomYLabelEntryEnabled

        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)

        lineChart.description.text = "Days"
        lineChart.setNoDataText("No forex yet!")

        lineChart.animateX(1800, Easing.EaseInExpo)

        Handler().postDelayed({ lineChart.invalidate() }, 200)
    }
}


