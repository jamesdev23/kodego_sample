package ph.kodego.rara.jamesnico.module_3.model

import ph.kodego.rara.jamesnico.module_3.R

class Student (var firstName:String = "Unknown", var lastName:String = "Unknown", var img:Int){
    var id : Int = 0
    constructor(): this("","", R.drawable.placeholder) {}
}