# Extended Navigation Drawer Sample

When creating a new Project with Android Studio multiple samples are offered. This repository is extended from "Navigation Drawer Activity".

Using this sample as it is one very central part is missing - how to switch between the "pages" using the navigation drawer. One approach is making use of `Fragment` classes. 

If we decide for using Fragments another question is how to think about the toolbar at the top.

> Is `Toolbar` part of the `DrawerActivity` or is each `Fragment` responsible for the look and feel of the toolbar?

As usual we can answer this with it depends on your application. This repository explains how to let each `Fragment` instance handle the look and feel of `Toolbar`. To be precise, each Fragment brings its own `Toolbar` instance and the one and only thing that the `DrawerActivity` knows about it, is `ActionBarDrawerToggle` ("Hamburger" icon) that is injected into the DrawerActivities `DrawerLayout`.

## Why you are doing this?

This approach allows to create completely different toolbar look and feel for each Fragment. In this example the first Fragment has a collapsing toolbar where the second hat a "normal" toolbar.

Click the [direct GIF link](http://fs5.directupload.net/images/170513/39lm6ie9.gif) to see how it looks like.

## Feedback

Feel free to make pull request if you know how to do better! :)
