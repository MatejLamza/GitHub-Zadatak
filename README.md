# GitHub-Zadatak

Project is pulling data from GitHub API and displaying it inside the recycler view. By clicking on image of project owner,
application is redirecting you to new screen where you can see additinal information about repository owner. Clicking on item itself,
application is redirecting you to screen where you can see additional information about specific repository. Both owner details screen 
and repository details screen have button that will redirect you to github web page to give you additional information about owner or repository.

This app is based on MVVM architecture and its using Retrofit to fetch data from github API, Dagger2 is used for dependency injection,
Room is used for caching and fetching data about repository owner, Kotlin coroutines are used for fetching and saving data on background thread.
