# Skeleton
Skeleton is made to help you start building a new app. It contains several features commonly found
in any app:
- a EULA flow
- a pager as home screen
- a list of draggable items that uses a recycler view
- help, about us, and settings screens
- use of shared preferences and databases for persistence


<img src="/media/flow.gif" alt="Skeleton user flow" width="368px" />

 
- Skeleton asks users to accept the EULA on start up. 
- Only when accepted, it presents a pager with two screens: summary and data.
- Data is an editable list of draggable items. It is persisted, including its reordering.
- Items can also be edited, and are persisted. 
- Views have both vertical and horizontal layouts.
- Settings has one field that is editable and persisted.
- Basic unit and component tests are also provided as basis for a testing suite.

## Usage
Make a copy of the project and use it as the basis for a new project in your editor of choice.
Rename the package, change the app's name, delete the components you do not need, add what you
miss, customize screens, customize the style...

## Technical Details
Skeleton follows the MVVM architecture, and the good practices of one activity, multiple fragments;
injecting dependencies; using repositories; and testing.
Further, the app is split into several components, the main ones: data (database, repositories,
data access objects...), domain (domain classes), features (app functionalities).

Other technical details:
- Code is written in Kotlin.
- The UI uses Android Material.
- Fragments are connected using Navigation with SafeArgs.
- Settings are persisted using Shared preferences.
- Data is displayed using a Recycler view and an adapter.
- Data is persisted in a SQLite database using Room, with a DAO and repository classes to manage it.
- Asynchronous tasks use Kotlin coroutines.
- Injection uses Hilt.
- Logging uses Timber.
- Testing uses JUnit.


This project is provided **AS-IS**.

This project is **MIT licensed**. [License.](license)