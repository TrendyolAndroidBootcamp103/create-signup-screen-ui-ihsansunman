package school.cactus.succulentshop

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import school.cactus.succulentshop.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    private val emailValidator = EmailValidator()
    private val usernameValidator = UsernameValidator()
    private val passwordValidator = PasswordValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.sign_up)

        binding.apply {
            signUpButton.setOnClickListener {
                passwordInputLayout.validate()
                usernameInputLayout.validate()
                emailInputLayout.validate()
            }

            logInAccountButton.setOnClickListener {
                navigateToLoginActivity()
            }
        }
    }

    private fun TextInputLayout.validate() {
        val errorMessage = validator().validate(editText!!.text.toString())
        error = errorMessage?.resolveAsString()
        isErrorEnabled = errorMessage != null
    }

    private fun Int.resolveAsString() = getString(this)

    private fun TextInputLayout.validator() = when (this) {
        binding.emailInputLayout -> emailValidator
        binding.usernameInputLayout -> usernameValidator
        binding.passwordInputLayout -> passwordValidator
        else -> throw IllegalArgumentException("Cannot find any validator for the given TextInputLayout")
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("isLoggedIn", true)
        startActivityForResult(intent, REQUEST_LOGIN_ACTIVITY)
    }

    companion object {
        const val REQUEST_LOGIN_ACTIVITY = 1001
    }
}