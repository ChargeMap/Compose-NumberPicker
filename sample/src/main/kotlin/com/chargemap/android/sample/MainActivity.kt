package com.chargemap.android.router.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chargemap.android.router.Router
import com.chargemap.android.router.sample.databinding.ActivityButtonBinding

class MainActivity : AppCompatActivity() {

    lateinit var ui: ActivityButtonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = ActivityButtonBinding.inflate(layoutInflater)

        processView()

        setContentView(ui.root)
    }

    private fun processView() {
        ui.mainButton.setOnClickListener {
            Router.of(this)
                .push(
                    Routes.Second
                )
        }
    }
}