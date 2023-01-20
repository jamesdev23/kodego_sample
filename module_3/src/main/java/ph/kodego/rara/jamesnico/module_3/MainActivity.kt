package ph.kodego.rara.jamesnico.module_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
//        init()

        studentAdapter = StudentAdapter(students)
        binding.list.layoutManager = LinearLayoutManager(applicationContext)
//        binding.list.layoutManager = GridLayoutManager(applicationContext,2)
        binding.list.adapter = studentAdapter

        binding.addStudentButton.setOnClickListener {
            studentAdapter.addStudent(Student(
                    binding.studentFirstname.text.toString(),
                    binding.studentLastname.text.toString(),
                    R.drawable.placeholder))
        }

        var swipeCallback = SwipeCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        swipeCallback.studentAdapter = studentAdapter
        itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.list)


    }

    fun init(){
        students.add(Student("Dave", "Navor", R.drawable.icon_1))
        students.add(Student("Victor", "Yu", R.drawable.icon_2))
        students.add(Student("Jp", "Soriano", R.drawable.icon_1))
        students.add(Student("Rene", "Palma", R.drawable.icon_2))
        students.add(Student("Joni", "James", R.drawable.icon_1))
        students.add(Student("Janreign", "Aragon", R.drawable.icon_2))
        students.add(Student("John Rey", "Balais", R.drawable.icon_1))
        students.add(Student("James Nico", "Rara", R.drawable.icon_2))
        students.add(Student("Matthew", "Mottos", R.drawable.icon_1))
    }
}