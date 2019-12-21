# Bitcoin Prices App

Bitcoin Prices is an *Android* application built on Kotlin which shows the current bitcoin exchange rates in an specific interval of time.
The app consumes information from [Blockchain API](https://www.blockchain.com/api/charts_api).


## Project Structure

This project uses the concept of [Clean Arquitecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) which will devide the project into theses layers.

* presentation
* domain
* data

<img src="https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg" width="500" height="340">

The project uses TDD paradigm based on this [practical example](https://resocoder.com/2019/08/27/flutter-tdd-clean-architecture-course-1-explanation-project-structure/) applied to the particular use case of Bitcoin Prices

## Architecture

Bitcoin Prices works with the patter [MVVM](https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1) recommend by [Android Oficial Site](https://developer.android.com/jetpack/docs/guide#recommended-app-arch)
that is adapted to applies the clean arquitecture concept. 

#### Bitcoin Prices general arch diagram.

<img src="https://raw.githubusercontent.com/anamarin09041995/BitcoinPricesApp/master/diagrams/archDiagram.png" width="600" height="720">

### Prerequisites

minSdkVersion 21

### Demo

<img src="https://github.com/anamarin09041995/BitcoinPricesApp/blob/master/diagrams/offlineBehavior.jpeg" width="300" height="600">

### Built With

Bitcoin uses the following third party libraries.

* [RxJava](https://github.com/ReactiveX/RxJava)
* [Dagger2](https://dagger.dev/users-guide)
* [Android Arch Components](https://developer.android.com/jetpack/#architecture-components) - Room, LiveData, View Model
* [Retrofit ](https://square.github.io/retrofit/)
* [Chart](https://weeklycoding.com/mpandroidchart/)
* [Mockito](https://site.mockito.org/)

## Running the tests

The following tests was written.

![alt text](https://github.com/anamarin09041995/BitcoinPricesApp/blob/master/diagrams/testResults.jpeg)

## Contributing

Feedback is always welcome!

## Authors

* *Ana Marin* - Mobile Developer - [LinkedIn profile](http://linkedin.com/in/ana-marin-b988b1140)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
