package ph.kodego.rara.jamesnico.kodegosample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import com.google.android.material.snackbar.Snackbar
import ph.kodego.rara.jamesnico.kodegosample.databinding.ActivityHomeBinding
import ph.kodego.rara.jamesnico.kodegosample.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profilepicture.setBackgroundResource(R.drawable.sample)

        var value = binding.fullnametext.text

//        binding.fullnametext.setText("Full Name")
        binding.fullnametext.setText("Full Name")
//        binding.fullnametext.setHint("Full Name")
//        binding.fullnametext.hint = "Full Name"

        var spinnerValue = binding.usertype.selectedItem.toString()

        binding.usertype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var data = binding.usertype.selectedItem.toString()
                Snackbar.make(binding.root, data,Snackbar.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Snackbar.make(binding.root,"No Change in the choice", Snackbar.LENGTH_SHORT).show()
            }
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.userType2,
            android.R.layout.simple_spinner_item
        ).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.usersubtype.adapter = adapter
        }

        var locations = arrayOf("Marikina", "Makati", "Mandaluyong", "Taguig", "Rizal")
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            locations
        ).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.location.adapter = adapter
        }

        binding.seek.setOnSeekBarChangeListener( object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    if(seekBar != null){
                        if(progress > 50 && progress < seekBar.max){
                            Snackbar.make(binding.root,"Seek : $progress", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Snackbar.make(binding.root,"Seek : start", Snackbar.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Snackbar.make(binding.root,"Seek : stop", Snackbar.LENGTH_SHORT).show()
            }


        })

        binding.btnRegister.setOnClickListener{
            var ratingValue = binding.rating.rating
            var seekValue = binding.seek.progress

//            Snackbar.make(binding.root,"Rating : ${ratingValue}", Snackbar.LENGTH_SHORT).show()
            Snackbar.make(binding.root,"Seek : $seekValue", Snackbar.LENGTH_SHORT).show()
        }



    }
}