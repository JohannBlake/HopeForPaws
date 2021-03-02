# HopeForPaws

An Android demo app submitted to Google's contest "Dev Challenge" for the purpose of showcasing the beta release of Jetpack Compose.

The app displays a list of cats that can be adopted. Clicking on a list item displays details about the pet including their name, gender, date of birth, color, and a description about the animal. An image gallery is included and some cats have more images than others. There is also a button that you can click on to adopt the cat. Clicking it opens the browser to the site where the adoption process begins.

The app is written in Kotlin and uses only Jetpack Compose to create the UIs. Data is generated on a backend using Wirespec (wirespec.dev).  Wirespec is a free online service that lets you easily and quickly create APIs at endpoints that you can define. I am the creator and owner of Wirespec

When the app starts, a splash screen is shown. It runs for 4 seconds. This is a fixed duration and is unfortunately needed as the backend, which is running on a Google Cloud Platform App Engine, probably needs to boot up. Once the instance is running though, it's quite fast in delivering the response.

The Wirespec API project can be viewed at:
[https://wirespec.dev/Wirespec/projects/apis/AdoptPets/apis/getPets](https://wirespec.dev/Wirespec/projects/apis/AdoptPets/apis/getPets)

The API response is generated here:
[https://api.wirespec.dev/wirespec/adoptpets/getpets](https://api.wirespec.dev/wirespec/adoptpets/getpets)

##About the Architecture
The following framework components were used for the initial release of this app:

* Compose 1.0.0-beta01 
* Paging Library 3.0
* Retrofit

There is only one activity which is the main activity. When you click on a list item, instead of using the navigation framework to navigate to the details screen, the details screen is simply displayed. This was done to keep things simple but also to illustrate that you don't need to write much code to create navigation in your app. The other benefit in this app is that the state of the list is kept intact without any work required. That means that when you return to the list, the scroll position is the same as it was before.

The list is generated as an endless list using a LazyColumn and the grid is created with nothing more than Rows and Columns although some fancy calculation is required to make this work, as the LazyColumn is only designed to handle one item per row.