# Skeleton
Skeleton is made to help you start building a new app. It contains several features commonly found
in any app, and more:
- a EULA flow
- a pager
- a screen displaying a list of editable items, stored in a local database
- a screen displaying a list of items, retrieved from a web service  
- help, about, and settings screens
- shared preferences for persisting settings
- horizontal and vertical layouts to adapt the UI to users devices
- unit and component tests


<img src="/media/flow.gif" alt="Skeleton user flow" width="368px" />

 
Skeleton asks users to accept the EULA on start up.  After being accepted, it presents a pager with
three screens: Items, IStats, Recipes, where:
- Items is an editable list of draggable items persisted locally.
- IStats is information about Items.
- Recipes is a list retrieved from a web service.

## Usage
Make a copy of the project and use it as the basis for a new project in your editor of choice.
Rename the package, change the app's name, delete the components you do not need, add what you
miss, customize screens, customize the style...

If you want to try the service used for fetching recipes, you will have to create an account
on the service website. Then, create a file named "api.properties" in the root directory of the
project, and define properties appId and appKey with the values associated to your account. The
gradle build script gets those properties and makes them available to the app, where they are
used to connect to the service. You can find the details about the service in the relevant class.
Sample api.properties file:
```
appId="8sh282gg"
appKey="a89fuafmnau893r2y39ysfsfs"
```

## Technical Details
Skeleton follows the MVVM architecture, and the good practices of one activity, multiple fragments;
injecting dependencies; using repositories; and testing.
Further, the app is split into several blocks, the main ones: data (database, repositories,
data access objects...), domain (domain classes), features (app functionalities).

Other technical details:
- Code is written in Kotlin.
- The UI uses Android Material.
- Fragments are connected using Navigation with SafeArgs.
- Settings are persisted using Shared Preferences.
- Data is displayed using a Recycler view and adapters.
- Local data is persisted in a SQLite database using Room, with a DAO and repository classes to manage it.
- Remote data is fetched from a web service using Retrofit, and built into domain classes with Moshi.  
- Asynchronous tasks use Kotlin coroutines.
- Injection uses Hilt.
- Logging uses Timber.
- Testing uses JUnit.


This project is provided **AS-IS**.

This project is **MIT licensed**. [License.](license)