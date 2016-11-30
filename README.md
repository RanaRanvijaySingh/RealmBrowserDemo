# RealmBrowserDemo

Table name : **Book**<br/>
Columns : **title, cost**

### Snapshot
![screenshot from 2016-11-30 11 33 56](https://cloud.githubusercontent.com/assets/4836122/20742288/35c3e384-b6f5-11e6-8d1c-ca6689764075.png)
![screenshot_20161130-113233](https://cloud.githubusercontent.com/assets/4836122/20742287/35c3559a-b6f5-11e6-8ed2-c1508c0128df.png)


### Instructions
We will directly jump to the point where Realm is properly integrated in your app and you have a running build in your phone. <br/>
If you don't have that set up, than simply clone this repo and run the build on your phone.

**build.gradle**


```
repositories {
    maven {
        url 'https://github.com/uPhyca/stetho-realm/raw/master/maven-repo'
    }
}

dependencies {
 	...
 	compile 'com.facebook.stetho:stetho:1.3.1'
    compile 'com.uphyca:stetho_realm:0.9.0'
}
```

**MyApplication**


```
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }
}
```
**DONE! Thats's all you have to do.**

Now to view the database <br/>
* Launch **Chrome**<br/>
* In address bar give this **chrome://inspect**, you will see the application listed by its package name.<br/>
* Click on **inspect** button.
* Goto **Resources > Web SQL > default.realm** , you can view all your data here.  

**Issues faced and resolved.** <br/>
StackLink : App crash - was getting [java-lang-noclassdeffounderror-failed-resolution-of-lio-realm-internal-shared](http://stackoverflow.com/questions/40866130/java-lang-noclassdeffounderror-failed-resolution-of-lio-realm-internal-sharedr/40866797?noredirect=1#comment68951257_40866797)
