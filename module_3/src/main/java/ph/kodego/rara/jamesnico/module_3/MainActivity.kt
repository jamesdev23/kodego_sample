package ph.kodego.rara.jamesnico.module_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ph.kodego.rara.jamesnico.module_3.adapter.StudentAdapter
import ph.kodego.rara.jamesnico.module_3.dao.StudentDAO
import ph.kodego.rara.jamesnico.module_3.dao.StudentDAOSQLImpl
import ph.kodego.rara.jamesnico.module_3.databinding.ActivityMainBinding
import ph.kodego.rara.jamesnico.module_3.model.Student

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter:StudentAdapter
    private var students: ArrayList<Student> = ArrayList()
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var dao:StudentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = StudentDAOSQLImpl(applicationContext)
        students = dao.getStudents()
        students = dao.getStudentByLastNameAndFirstName("Marco","Valmores")
//        init()

        var studentContacts = dao.getStudentsWithContacts()

        studentAdapter = StudentAdapter(students, this)
        binding.list.layoutManager = LinearLayoutManager(applicationContext)
//        binding.list.layoutManager = GridLayoutManager(applicationContext,2)
        binding.list.adapter = studentAdapter

        binding.addStudentButton.setOnClickListener {
            val student = Student()

            student.firstName = binding.studentFirstname.text.toString()
            student.lastName = binding.studentLastname.text.toString()

            dao.addStudent(student)
            students = dao.getStudents()
            studentAdapter.updateStudents(students)



//            studentAdapter.addStudent(Student(
//                    binding.studentFirstname.text.toString(),
//                    binding.studentLastname.text.toString(),
//                    R.drawable.placeholder))
        }

        var swipeCallback = SwipeCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        swipeCallback.studentAdapter = studentAdapter
        itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.list)

        //search
        binding.searchStudent.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
//                Toast.makeText(this@MainActivity,"$newText", Toast.LENGTH_SHORT).show()
//                studentAdapter.filter.filter(newText)
                students = dao.searchStudentsByLastName(newText!!)
                studentAdapter.updateStudents(students)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
//                Toast.makeText(this@MainActivity,"Search: $query", Toast.LENGTH_SHORT).show()
                studentAdapter.filter.filter(query)

                return false
            }
        })

    }

//    fun init(){
//        students.add(Student("Dave", "Navor", R.drawable.icon_1))
//        students.add(Student("Victor", "Yu", R.drawable.icon_2))
//        students.add(Student("Jp", "Soriano", R.drawable.icon_1))
//        students.add(Student("Rene", "Palma", R.drawable.icon_2))
//        students.add(Student("Joni", "James", R.drawable.icon_1))
//        students.add(Student("Janreign", "Aragon", R.drawable.icon_2))
//        students.add(Student("John Rey", "Balais", R.drawable.icon_1))
//        students.add(Student("James Nico", "Rara", R.drawable.icon_2))
//        students.add(Student("Matthew", "Mottos", R.drawable.icon_1))
//    }
}