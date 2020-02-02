package com.sumygg.ststhespire

import com.sumygg.ststhespire.view.MainView
import javafx.application.Application
import mu.KotlinLogging
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import tornadofx.*
import kotlin.reflect.KClass

@SpringBootApplication(scanBasePackages = ["com.sumygg.ststhespire"])
class Main : App(MainView::class) {

    private val logger = KotlinLogging.logger {}

    private lateinit var context: ConfigurableApplicationContext

    override fun init() {
        this.context = SpringApplication.run(this.javaClass)
        context.autowireCapableBeanFactory.autowireBean(this)
        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>): T = context.getBean(type.java)
        }
    }

    override fun stop() {
        logger.info { "Stop Application..." }
        super.stop()
        context.close()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(Main::class.java, *args)
        }
    }
}
