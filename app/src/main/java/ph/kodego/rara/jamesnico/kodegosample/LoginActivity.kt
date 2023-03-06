package ph.kodego.rara.jamesnico.kodegosample

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ph.kodego.rara.jamesnico.kodegosample.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var username: String
    private lateinit var password: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.title.text = "Main Page"

        binding.btnSubmit.setOnClickListener {

            username = binding.usernametext.text.toString()
            password = binding.passwordtext.text.toString()

            //Snackbar.make(binding.root, "$username - $password", Snackbar.LENGTH_SHORT).show()
            //Toast.makeTest(applicationContext, "Toast Submit", Toast.LENGTH_SHORT).show()

            var goToHome = Intent(this, RegisterActivity::class.java)


            val bundle = Bundle()
            bundle.putString("username",username)
            bundle.putString("password",password)
            goToHome.putExtras(bundle)

            goToHome.putExtra("something","Extra")

            startActivity(goToHome)
            finish()
        }
    }
}