package ph.kodego.rara.jamesnico.module_3.adapter

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ph.kodego.rara.jamesnico.module_3.R
import ph.kodego.rara.jamesnico.module_3.dao.StudentDAO
import ph.kodego.rara.jamesnico.module_3.dao.StudentDAOSQLImpl
import ph.kodego.rara.jamesnico.module_3.databinding.DialogueUpdateStudentBinding
import ph.kodego.rara.jamesnico.module_3.databinding.StudentItemBinding
import ph.kodego.rara.jamesnico.module_3.model.Student

class StudentAdapter (var students: ArrayList<Student>, var activity: Activity)
    : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(){

    fun addStudent(student: Student){
        students.add(0,student)
        notifyItemInserted(0)
    }

    fun removeStudent(position: Int){
        students.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateStudents(newStudents: ArrayList<Student>){
        students.clear()
        students.addAll(newStudents)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentAdapter.StudentViewHolder {
        val itemBinding = StudentItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return StudentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StudentAdapter.StudentViewHolder,
                                  position: Int) {
        holder.bindStudent(students[position])
    }

    inner class StudentViewHolder(private val itemBinding: StudentItemBinding)
        : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        var student = Student()

        init{
            itemView.setOnClickListener(this)
        }

        fun bindStudent(student: Student) {
            this.student = student

            itemBinding.studentName.setText("${student.lastName}, ${student.firstName}")
            itemBinding.btnDeleteRow.setOnClickListener {
                Snackbar.make(itemBinding.root,
                    "Delete by button",
                    Snackbar.LENGTH_SHORT
                ).show()

                removeStudent(adapterPosition)
            }
            itemBinding.profilePicture.setImageResource(student.img)
        }

        override fun onClick(v: View?) {
            Snackbar.make(itemBinding.root,
                "${student.lastName}, ${student.firstName}",
                Snackbar.LENGTH_SHORT
            ).show()

//            removeStudent(adapterPosition)
//            showAlertDialogue()
            showCustomDialogue()
        }

        fun showCustomDialogue(): AlertDialog {
            return activity?.let {
                val builder = AlertDialog.Builder(it)
                val dialogUpdateStudentBinding: DialogueUpdateStudentBinding =
                    DialogueUpdateStudentBinding.inflate(it.layoutInflater)

                with(dialogUpdateStudentBinding) {
                    studentLastnameUpdate.setText(student.lastName)
                    studentFirstnameUpdate.setText(student.firstName)
                }

                with(builder) {
                    setPositiveButton("Update",
                        DialogInterface.OnClickListener { dialog, id ->
                            val dao: StudentDAO = StudentDAOSQLImpl(activity.applicationContext)
                            dao.updateStudent(student.id, student)

                            student.lastName =
                                dialogUpdateStudentBinding.studentLastnameUpdate.text.toString()
                            student.firstName =
                                dialogUpdateStudentBinding.studentFirstnameUpdate.text.toString()

                            updateStudents(dao.getStudents())
                            notifyItemChanged(adapterPosition)
                        })
                    setNegativeButton("Cancel",
                        DialogInterface.OnClickListener { dialog, id ->
                            // do something
                        })
                    setView(dialogUpdateStudentBinding.root)
                    create()
                }
            } ?: throw IllegalStateException("Activity cannot be null")
        }

        fun showAlertDialogue(){
            val alertDialog = AlertDialog.Builder(activity)

            alertDialog.apply {
                setIcon(R.drawable.icon_1)
                setTitle("Student Background")
                setMessage("Student Record")
//                setPositiveButton("Positive") { _, _ ->
//                    toast("clicked positive button")
//                }
//                setNegativeButton("Negative") { _, _ ->
//                    toast("clicked negative button")
//                }
                setNeutralButton("Close") { _, _ ->
                    toast("clicked close button")
                }
            }.create().show()
        }

        private fun toast(text:String) = Toast.makeText(activity.applicationContext, text, Toast.LENGTH_SHORT).show()


    }
}