package com.example.triviaapp.ui.quizactivity

import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.triviaapp.R
import com.example.triviaapp.databinding.ActivityQuizBinding
import com.example.triviaapp.databinding.DilaogAnswerSubmittedBinding
import com.example.triviaapp.db.Quiz
import com.example.triviaapp.injection.component.AppComponent
import com.example.triviaapp.ui.base.BaseActivity
import com.example.triviaapp.ui.history.HistoryActitvity
import com.example.triviaapp.ui.main.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class QuizActivity : BaseActivity<ActivityQuizBinding, QuizViewModel>() {
    override fun layoutId(): Int = R.layout.activity_quiz
    override fun getViewModelClass(): Class<QuizViewModel> = QuizViewModel::class.java


    //passing passing to dagger
    override fun injectActivity(appComponent: AppComponent) {
        appComponent.homeComponent().create().inject(this)
    }

    private val sharedPrefFile = "triviapref"
    lateinit var sharedPreferences: SharedPreferences
    var firstAns = ""
    var secondAns = ""
    var username = ""
    var atleastOneIsSelected = false
    var firstAnsSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        clickListener()
        addObservers()
    }

    //to initalize ui
    private fun initUi() {
        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        setFisrtQuest()
        username = sharedPreferences.getString("username", "")!!
    }

    //for button or other clicks or touch
    private fun clickListener() {
        binding.rgroupFirstQuestion.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            val radio: RadioButton = findViewById(i)
            if (radio.text.toString().trim().equals(getString(R.string.sachin_tendulkar))) {
                firstAns = getString(R.string.sachin_tendulkar)
                firstAnsSelected = true
            } else if (radio.text.toString().trim().equals(getString(R.string.virat_kolli))) {
                firstAns = getString(R.string.virat_kolli)
                firstAnsSelected = true
            } else if (radio.text.toString().trim().equals(getString(R.string.adam_gilchirst))) {
                firstAns = getString(R.string.adam_gilchirst)
                firstAnsSelected = true
            } else if (radio.text.toString().trim().equals(getString(R.string.jacques_kallis))) {
                firstAns = getString(R.string.jacques_kallis)
                firstAnsSelected = true
            }
        })

        binding.btNext.setOnClickListener {
            if (firstAnsSelected == true) {
                binding.clFirstQuestion.visibility = View.GONE
                binding.clSecondQuestion.visibility = View.VISIBLE
                setSecondQuest()
            } else {
                uiUtil.showToast("Please Select Some Answer To Move To Next Question")
            }

        }
        binding.btSubmit.setOnClickListener {
            getSecondAns()
            if (atleastOneIsSelected == true) {
                viewModel.addQuiz(
                    Quiz(
                        username,
                        firstAns,
                        secondAns,
                        getFormatedDate(getdate()).toString()
                    )
                )
            } else {
                uiUtil.showToast("Please Select Atleast One Answer")
            }

        }
    }


    //get second ans
    private fun getSecondAns() {
        if (binding.chWhite.isChecked) {
            secondAns = secondAns + " " + getString(R.string.white) + " , "
            atleastOneIsSelected = true
        }
        if (binding.chYellow.isChecked) {
            secondAns = secondAns + " " + getString(R.string.yellow) + " , "
            atleastOneIsSelected = true
        }
        if (binding.chGreen.isChecked) {
            secondAns = secondAns + " " + getString(R.string.green) + " , "
            atleastOneIsSelected = true
        }
        if (binding.chOrange.isChecked) {
            atleastOneIsSelected = true
            secondAns = secondAns + " " + getString(R.string.orange) + " , "
        }
    }

    //set first ques via visibilty
    private fun setFisrtQuest() {
        binding.tvQuizQuestion.text = getString(R.string.first_question)
        binding.clFirstQuestion.visibility = View.VISIBLE
        binding.clSecondQuestion.visibility = View.GONE
    }

    //hide first ques and show second ques via visibilty
    private fun setSecondQuest() {
        binding.tvQuizQuestion.text = getString(R.string.second_question)
        binding.clFirstQuestion.visibility = View.GONE
        binding.clSecondQuestion.visibility = View.VISIBLE
    }


    //observe data from viewmodel
    override fun addObservers() {
        viewModel.addquiz.observe(this, {
            if (it.equals("1")) {
                openDialog()
            }
        })
    }

    //showing dilaog on both ques submission
    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        val binding: DilaogAnswerSubmittedBinding =
            DilaogAnswerSubmittedBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(binding.getRoot())
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        dialog.show()
        binding.btStartAgain.setOnClickListener {
            navigator.startActivityClearCurrent(MainActivity::class.java, true)
        }
        binding.btSeeHistory.setOnClickListener {
            navigator.startActivity(HistoryActitvity::class.java, true)
        }
    }

    //geting date in format
    private fun getdate(): String {
        val date = Date()
        val DateFor = SimpleDateFormat("dd MMMM h:mm a", Locale.ENGLISH)
        val stringDate: String = DateFor.format(date)
        return stringDate
    }

    //getiing superscript at date end by passing date to it
    fun getFormatedDate(date: String): String {
        date.let {
            try {
                val parser = SimpleDateFormat("dd MMMM h:mm a", Locale.getDefault())
                val formatter = SimpleDateFormat("dd MMMM h:mm a", Locale.getDefault())
                val dateArray = formatter.format(parser.parse(it)).split(" ").toTypedArray()
                val formatedDate = String.format(
                    "${dateArray[0]}${
                        dateArray[0].toInt().ordinalAbbrev()
                    } ${dateArray[1]} ${dateArray[2]} ${dateArray[3]}"
                )

                return formatedDate
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return date
    }

    //method to get supercript ordinal
    fun Int.ordinalAbbrev() =
        if (this % 100 / 10 == 1) "th"
        else when (this % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }


}