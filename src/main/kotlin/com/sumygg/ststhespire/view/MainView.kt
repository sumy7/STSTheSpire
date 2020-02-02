package com.sumygg.ststhespire.view

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDialog
import com.jfoenix.controls.JFXDialogLayout
import javafx.scene.layout.StackPane
import javafx.scene.text.Text
import tornadofx.*


class MainView : View("MainView") {
    override val root: StackPane by fxml("/views/MainView.fxml")

    private val loadButton: JFXButton by fxid("btn_load")

    private val saveButton: JFXButton by fxid("btn_save")

    init {
        loadButton.action {
            val messge = JFXDialogLayout().apply {
                this.setHeading(Text("Hello"))
                this.setBody(Text("Hello World!"))
            }

            val mag = JFXDialog(root, messge, JFXDialog.DialogTransition.CENTER)

            val button1 = JFXButton("close")
            button1.action {
                mag.close()
            }
            messge.setActions(button1)
            mag.show()

        }
    }
}
