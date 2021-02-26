package dev.wirespec.hopeforpaws

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dev.wirespec.hopeforpaws.utils.NetworkUtils

class App : Application() {

    private val activityLifecycleTracker: AppLifecycleTracker = AppLifecycleTracker()

    override fun onCreate() {
        super.onCreate()
        context = this

        registerActivityLifecycleCallbacks(activityLifecycleTracker)
        NetworkUtils.startNetworkCallback()
    }

    companion object {
        lateinit var context: App
        var networkAvailable: Boolean = false
    }

    // Returns the current activity.
    var currentActivity: Activity?
        get() = activityLifecycleTracker.currentActivity
        private set(value) {}

    /**
     * Callbacks for handling the lifecycle of activities.
     */
    class AppLifecycleTracker : ActivityLifecycleCallbacks {

        private var currentAct: Activity? = null

        var currentActivity: Activity?
            get() = currentAct
            private set(value) {}

        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
            currentAct = activity
        }

        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityStopped(activity: Activity) {
            if ((currentAct != null) && (activity == currentAct))
                currentAct = null
        }

        override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        }

        override fun onActivityDestroyed(p0: Activity) {
        }
    }
}