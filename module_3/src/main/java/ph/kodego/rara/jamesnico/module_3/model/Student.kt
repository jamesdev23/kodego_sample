package ph.kodego.rara.jamesnico.module_3.model

import ph.kodego.rara.jamesnico.module_3.R

class Student (var firstName:String = "Unknown", var lastName:String = "Unknown", var img:Int){
    var id:Int = 0
    var yearStarted:Int = 0
    var course:String = ""
    constructor(): this("","", R.drawable.placeholder) {}
}

class StudentContacts(){
    var student:Student = Student()
    var contacts = ArrayList<Contact>()
}