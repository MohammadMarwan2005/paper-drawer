package com.alaishat.mohammad.papersdrawerv2

/**
 * Created by Mohammad Al-Aishat on Jun/10/2024.
 * Papers Drawer V2 Project.
 */
interface Destination {
    val route: String
}

object DrawYourPaper: Destination{
    override val route: String = "DrawYourPaper"
}
object RectanglePossiblePapers: Destination{
    override val route: String = "RectanglePossiblePapers"
}
object MyPapers: Destination{
    override val route: String = "MyPapers"
}