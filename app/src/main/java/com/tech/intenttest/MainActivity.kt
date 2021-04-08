package com.tech.intenttest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn_dial.setOnClickListener {
            var mobile_number=et_mobile_number.text.toString()

            var code=country_picker.selectedCountryCode
            Log.d("Number",mobile_number+"  "+code)
            var complete_mobile_number=code+mobile_number

            if(mobile_number.isEmpty())
            {

                Toasty.warning(this, "please enter mobile number",Toasty.LENGTH_SHORT, true).show();
            }else
            {
                //implicit intents
                var intent= Intent()
                //setData is used to  transfer the data from one activity to nather activity
                intent.data= Uri.parse("tel:${complete_mobile_number}")
                intent.action=Intent.ACTION_DIAL
                Toasty.success(this,"sucess",Toasty.LENGTH_SHORT,true).show()
                startActivity(intent)
               }
            Log.d("Number2",mobile_number+"  "+code)
        }

        btn_second.setOnClickListener {
            var intent=Intent(this,MySecondActivity::class.java)
            var name=et_message.text.toString()
            intent.putExtra("key",name)//to send the String type od data to anather activity
            startActivity(intent)
         //   startActivity(Intent(this,MySecondActivity::class.java))
        }

        //in.startv.hotstar
        btn_hotstar.setOnClickListener {
            var intent=packageManager.getLaunchIntentForPackage("in.startv.hotstar")
            startActivity(intent)
        }
        //in.ideastoday
        btn_daily.setOnClickListener {
            var intent=packageManager.getLaunchIntentForPackage("in.ideastoday")
            startActivity(intent)
        }

        //com.facebook.katana
        btn_facebook.setOnClickListener {
            try
            {
                var intent=packageManager.getLaunchIntentForPackage("com.facebook.katana")
                startActivity(intent)

            }catch(e:Exception)
            {

               //write the code to take user to the play store to install that particular app

                var intent=Intent()
                var url="https://play.google.com/store/apps/details?id=com.facebook.katana"
                intent.action=Intent.ACTION_VIEW//to call browser
                intent.data=Uri.parse(url)
                startActivity(intent)


                Toast.makeText(this,"app is not installed",Toast.LENGTH_LONG).show()
            }

        }

    }
}