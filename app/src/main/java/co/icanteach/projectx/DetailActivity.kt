package co.icanteach.projectx
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import co.icanteach.projectx.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val name = intent.getStringExtra(NAME)
        val img = intent.getStringExtra(IMG)
        val occupation = intent.getStringArrayListExtra(OCCU)
        val status = intent.getStringExtra(STATUS)
        val nickname = intent.getStringExtra(NICK)
        val appearance = intent.getIntegerArrayListExtra(APPEA)
        var app = StringBuffer()
        var occ = StringBuffer()

        for (a in appearance) {
            app.append("$a ")
        }

        for (a in occupation) {
            occ.append("$a ")
        }

        binding.appearence.text = "Season appearance: $app"
        binding.tvShowName.text = "Name: $name"
        Glide.with(this)
            .load(img)
            .into(binding.imageUrl)
        binding.status.text = "Status: $status"
        binding.nickname.text = "Nickname: $nickname"
        binding.occupation.text = "Occupation: $occ"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        private val INTENT_USER_ID = "user_id"

        private const val NAME = "name"
        private const val IMG = "img"
        private const val OCCU = "occupation"
        private const val STATUS = "status"
        private const val NICK = "nickname"
        private const val APPEA = "appearance"

        fun newIntent(
            context: Context, name: String,
            img: String,
            occupation: ArrayList<String>,
            status: String,
            nickname: String,
            appearance: ArrayList<Int>
        ): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(NAME, name)
            intent.putExtra(IMG, img)
            intent.putExtra(OCCU, occupation)
            intent.putExtra(STATUS, status)
            intent.putExtra(NICK, nickname)
            intent.putExtra(APPEA, appearance)
            return intent
        }
    }

}
