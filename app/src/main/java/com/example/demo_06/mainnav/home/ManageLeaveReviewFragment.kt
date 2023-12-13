package com.example.demo_06.mainnav.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentManageLeaveReviewBinding
import com.example.demo_06.mainnav.accountPublic0
import com.example.demo_06.mainnav.appAuthorityPublic
import com.example.demo_06.model.HolidayFinalReviewDeniedReq
import com.example.demo_06.model.HolidayReviewAcceptReq
import com.example.demo_06.model.HolidayReviewDeniedReq
import com.example.demo_06.model.HolidayReviewReq
import com.example.demo_06.network.RequestBuilder
import com.example.demo_06.network.api.User
import com.example.demo_06.network.res.BaseResponse
import com.example.demo_06.network.res.HolidayAcquire
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class ManageLeaveReviewFragment: BaseFragment<FragmentManageLeaveReviewBinding, PublicViewModel>(
    FragmentManageLeaveReviewBinding::inflate,
    PublicViewModel::class.java,
    true
){



    override fun initFragment(
        binding: FragmentManageLeaveReviewBinding,
        viewModel: PublicViewModel?,
        savedInstanceState: Bundle?
    ) {

//      ユーザーのアカウントを取得
        val myPersonalNo = accountPublic0

        val myAppAuthority = appAuthorityPublic

//      休暇種類の表示
        fun vacationNoShow(vacationNo: String): String{
            if(vacationNo == "11"){
                return "私用"
            }else if(vacationNo == "12"){
                return "体調不良"
            }else if(vacationNo == "13"){
                return "振替"
            }else{
                return "他"
            }
        }

//      審査画面
        fun showAlertDialog(
            workSpot: String,
            personalNo: String,
            startTime: String,
            endTime: String,
            vocationNo: String,
            reason: String,
            calendarNo: String,
            refusal: String
        ) {

//          審査後の画面更新
            fun updateList(){

//              画面の初期化
                binding.tableLayout1.removeAllViews()
//              審査待ちの休暇申込を検索
                RequestBuilder().getAPI(User::class.java).HolidayReview(HolidayReviewReq(myPersonalNo))
                    .enqueue(object : Callback<BaseResponse<List<HolidayAcquire>>> {
                        @SuppressLint("ResourceType", "SetTextI18n")
                        override fun onResponse(
                            call: Call<BaseResponse<List<HolidayAcquire>>>?,
                            response: Response<BaseResponse<List<HolidayAcquire>>>?
                        ) {
//                          検索結果を確認
                            response?.let {
                                if(it.body().data != null) {
                                    if(it.body().status == "200"){
//                                      検索結果ありの場合
                                        if(it.body().data != null) {
                                            it.body().data.let {

                                                it?.forEach {item ->

                                                    val row = TableRow(requireContext())
                                                    row.setPadding(0, 40, 0, 40)
//                                                  現場をテーブルに加入
                                                    val member1 = TextView(requireContext())
                                                    member1.textSize = 20F
                                                    member1.width = 90
                                                    member1.text = item.selectedWorkSpot
                                                    row.addView(member1)
//                                                  社員番号をテーブルに加入
                                                    val member2 = TextView(requireContext())
                                                    member2.textSize = 20F
                                                    member2.width = 90
                                                    member2.text = item.regAuthor
                                                    row.addView(member2)
//                                                  開始時刻をテーブルに加入
                                                    val member3 = TextView(requireContext())
                                                    member3.textSize = 20F
                                                    member3.width = 90
                                                    member3.text = item.startYear.substring(2) + "/" + item.startMonth + "/" + item.startDay
                                                    row.addView(member3)
//                                                  終了時刻をテーブルに加入
                                                    val member4 = TextView(requireContext())
                                                    member4.textSize = 20F
                                                    member4.width = 90
                                                    member4.text = item.endYear.substring(2) + "/" + item.endMonth + "/" + item.endDay
                                                    row.addView(member4)

//                                                  検索結果のクリックで詳細画面を表示
                                                    member1.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
                                                    member2.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
                                                    member3.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
                                                    member4.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
//                                                  テーブルを審査画面に追加
                                                    binding.tableLayout1.addView(row)

                                                }

                                            }
                                        }

                                    }

                                }else {
                                    Toast.makeText(
                                        requireContext(),
                                        it.body().message,
                                        Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<BaseResponse<List<HolidayAcquire>>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                        }

                    })
            }

    //          審査後の画面更新
            fun updateFinalList(){

        //              画面の初期化
                binding.tableLayout1.removeAllViews()
        //              審査待ちの休暇申込を検索
                RequestBuilder().getAPI(User::class.java).HolidayFinalReview()
                    .enqueue(object : Callback<BaseResponse<List<HolidayAcquire>>> {
                        @SuppressLint("ResourceType", "SetTextI18n")
                        override fun onResponse(
                            call: Call<BaseResponse<List<HolidayAcquire>>>?,
                            response: Response<BaseResponse<List<HolidayAcquire>>>?
                        ) {
        //                          検索結果を確認
                            response?.let {
                                if(it.body().data != null) {
                                    if(it.body().status == "200"){
        //                                      検索結果ありの場合
                                        if(it.body().data != null) {
                                            it.body().data.let {

                                                it?.forEach {item ->

                                                    val row = TableRow(requireContext())
                                                    row.setPadding(0, 40, 0, 40)
        //                                                  現場をテーブルに加入
                                                    val member1 = TextView(requireContext())
                                                    member1.textSize = 20F
                                                    member1.width = 90
                                                    member1.text = item.selectedWorkSpot
                                                    row.addView(member1)
        //                                                  社員番号をテーブルに加入
                                                    val member2 = TextView(requireContext())
                                                    member2.textSize = 20F
                                                    member2.width = 90
                                                    member2.text = item.regAuthor
                                                    row.addView(member2)
        //                                                  開始時刻をテーブルに加入
                                                    val member3 = TextView(requireContext())
                                                    member3.textSize = 20F
                                                    member3.width = 90
                                                    member3.text = item.startYear.substring(2) + "/" + item.startMonth + "/" + item.startDay
                                                    row.addView(member3)
        //                                                  終了時刻をテーブルに加入
                                                    val member4 = TextView(requireContext())
                                                    member4.textSize = 20F
                                                    member4.width = 90
                                                    member4.text = item.endYear.substring(2) + "/" + item.endMonth + "/" + item.endDay
                                                    row.addView(member4)

        //                                                  検索結果のクリックで詳細画面を表示
                                                    member1.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
                                                    member2.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
                                                    member3.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
                                                    member4.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
        //                                                  テーブルを審査画面に追加
                                                    binding.tableLayout1.addView(row)

                                                }

                                            }
                                        }

                                    }

                                }else {
                                    Toast.makeText(
                                        requireContext(),
                                        it.body().message,
                                        Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<BaseResponse<List<HolidayAcquire>>>?, t: Throwable?) {
        //                        Log.e("TAG","NetWorkErr!")
                        }

                    })
            }

            val alertDialog = AlertDialog.Builder(requireContext()).create()

//          タイトルを設定
            alertDialog.setTitle("審査画面")
//          リニアレイアウトと設定
            val linearLayout = LinearLayout(requireContext())
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.setPadding(20, 20, 20, 20)
//          リニアレイアウトの縦と横を設定
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
//          現場の内容を設定
            val workSpotText = SpannableString("現場 : $workSpot\n")
//        workSpotText.setSpan(ForegroundColorSpan(Color.BLACK), 0, workSpotText.length, 0)
//          現場を設定
            val workSpotTextView = TextView(requireContext())
            workSpotTextView.textSize = 20F
            workSpotTextView.text = workSpotText
            linearLayout.addView(workSpotTextView, params)
//          社員番号の内容を設定
            val personalNoText = SpannableString("社員番号     : $personalNo\n")
//        personalNoText.setSpan(ForegroundColorSpan(Color.BLACK), 0, personalNoText.length, 0)
//          社員番号を設定
            val personalNoTextView = TextView(requireContext())
            personalNoTextView.textSize = 20F
            personalNoTextView.text = personalNoText
            linearLayout.addView(personalNoTextView, params)

//          開始時刻の内容を設定
            val startText = SpannableString("開始時刻     : $startTime\n")
//        startText.setSpan(ForegroundColorSpan(Color.BLACK), 0, startText.length, 0)
//          開始時刻を設定
            val startTextView = TextView(requireContext())
            startTextView.textSize = 20F
            startTextView.text = startText
            linearLayout.addView(startTextView, params)
//          終了時刻の内容を設定
            val endText = SpannableString("終了時刻     : $endTime\n")
//        endText.setSpan(ForegroundColorSpan(Color.BLACK), 0, endText.length, 0)
//          終了時刻を設定
            val endTextView = TextView(requireContext())
            endTextView.textSize = 20F
            endTextView.text = endText
            linearLayout.addView(endTextView, params)

//          休暇種類を設定
            val vocationNoText = SpannableString("休暇種類     : $vocationNo\n")
            val vocationNoTextView = TextView(requireContext())
            vocationNoTextView.textSize = 20F
            vocationNoTextView.text = vocationNoText
            linearLayout.addView(vocationNoTextView, params)
//          休暇理由を設定
            val reasonText = SpannableString("休暇理由     : $reason\n")
            val reasonTextView = TextView(requireContext())
            reasonTextView.textSize = 20F
            reasonTextView.text = reasonText
            linearLayout.addView(reasonTextView, params)

            val rejectReasonEditText = EditText(requireContext())
            if(myAppAuthority == "2"){
//          拒絕理由のタイトルを設定
                val rejectReasonText = SpannableString("却下理由     : ")
                val rejectReasonTextView = TextView(requireContext())
                rejectReasonTextView.textSize = 20F
                rejectReasonTextView.text = rejectReasonText
                linearLayout.addView(rejectReasonTextView, params)
//          拒絕理由を設定
                rejectReasonEditText.hint = "却下には却下理由が必要"
                linearLayout.addView(rejectReasonEditText, params)
            }
            else if(myAppAuthority == "10"){
                if(refusal != null){
                        val rejectText = SpannableString("現場審査     : 却下")
                        val rejectTextView = TextView(requireContext())
                        rejectTextView.textSize = 20F
                        rejectTextView.text = rejectText
                        linearLayout.addView(rejectTextView, params)
//          拒絕理由のタイトルを設定
                        val rejectReasonText = SpannableString("却下理由     : $refusal ")
                        val rejectReasonTextView = TextView(requireContext())
                        rejectReasonTextView.textSize = 20F
                        rejectReasonTextView.text = rejectReasonText
                        linearLayout.addView(rejectReasonTextView, params)
                }
                else{
                    val rejectReasonText = SpannableString("現場審査     : 承認")
                    val rejectReasonTextView = TextView(requireContext())
                    rejectReasonTextView.textSize = 20F
                    rejectReasonTextView.text = rejectReasonText
                    linearLayout.addView(rejectReasonTextView, params)
                }
            }

//          詳細画面にlinearLayoutと設定
            alertDialog.setView(linearLayout)

//          改修ボタンを設定
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "改修") { dialog, _ ->
                dialog.dismiss()
            }

//          却下ボタンを設定
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "却下") { dialog, _ ->
//              却下理由を取得
                val rejectReason = rejectReasonEditText.text.toString()

//              却下理由をチェック
                if(rejectReason.length > 50 && myAppAuthority == "2"){
                    Toast.makeText(requireContext(), "却下理由の桁数は50に超えていた", Toast.LENGTH_SHORT).show()
                }
                else if (rejectReason.isEmpty() && myAppAuthority == "2") {
                    Toast.makeText(requireContext(), "却下理由が必要", Toast.LENGTH_SHORT).show()
                } else {
                    if(myAppAuthority == "2"){

//                  休暇申込を却下
                        RequestBuilder().getAPI(User::class.java).HolidayReviewDenied(HolidayReviewDeniedReq(calendarNo, rejectReason))
                            .enqueue(object : Callback<BaseResponse<String>> {
                                override fun onResponse(
                                    call: Call<BaseResponse<String>>?,
                                    response: Response<BaseResponse<String>>?
                                ) {
//                              結果を確認
                                    response?.let {
                                        if(it.body().data != null) {
                                            if(it.body().status == "200"){
//                                          結果ありの場合
                                                if(it.body().data != null) {

//                                              画面更新
                                                    updateList()

                                                    Toast.makeText(
                                                        requireContext(),
                                                        "休暇申込を拒否した",
                                                        Toast.LENGTH_SHORT).show()

                                                }

                                            }

                                        }else {
                                            Toast.makeText(
                                                requireContext(),
                                                it.body().message,
                                                Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<BaseResponse<String>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                                }

                            })
                    }
                    else if(myAppAuthority == "10"){

//                  休暇申込を却下
                        RequestBuilder().getAPI(User::class.java).HolidayFinalReviewDenied(
                            HolidayFinalReviewDeniedReq(calendarNo)
                        )
                            .enqueue(object : Callback<BaseResponse<String>> {
                                override fun onResponse(
                                    call: Call<BaseResponse<String>>?,
                                    response: Response<BaseResponse<String>>?
                                ) {
//                              結果を確認
                                    response?.let {
                                        if(it.body().data != null) {
                                            if(it.body().status == "200"){
//                                          結果ありの場合
                                                if(it.body().data != null) {

//                                              画面更新
                                                    updateFinalList()

                                                    Toast.makeText(
                                                        requireContext(),
                                                        "休暇申込を拒否した",
                                                        Toast.LENGTH_SHORT).show()

                                                }

                                            }

                                        }else {
                                            Toast.makeText(
                                                requireContext(),
                                                it.body().message,
                                                Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<BaseResponse<String>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                                }

                            })
                    }

                    dialog.dismiss()
                }
            }

//          承認ボタンを設定
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "承認") { dialog, _ ->

                if(myAppAuthority == "2"){

//              休暇申込を承認
                    RequestBuilder().getAPI(User::class.java).HolidayReviewAccept(HolidayReviewAcceptReq(calendarNo))
                        .enqueue(object : Callback<BaseResponse<String>> {
                            override fun onResponse(
                                call: Call<BaseResponse<String>>?,
                                response: Response<BaseResponse<String>>?
                            ) {
//                          検索結果を確認
                                response?.let {
                                    if(it.body().data != null) {
                                        if(it.body().status == "200"){
//                                      結果ありの場合
                                            if(it.body().data != null) {

//                                          画面更新
                                                updateList()

                                                Toast.makeText(
                                                    requireContext(),
                                                    "休暇申込を承認した",
                                                    Toast.LENGTH_SHORT).show()
                                            }

                                        }

                                    }else {
                                        Toast.makeText(
                                            requireContext(),
                                            it.body().message,
                                            Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }

                            override fun onFailure(call: Call<BaseResponse<String>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                            }

                        })
                }
                else if(myAppAuthority == "10"){

//              休暇申込を承認
                    RequestBuilder().getAPI(User::class.java).HolidayFinalReviewAccept(HolidayReviewAcceptReq(calendarNo))
                        .enqueue(object : Callback<BaseResponse<String>> {
                            override fun onResponse(
                                call: Call<BaseResponse<String>>?,
                                response: Response<BaseResponse<String>>?
                            ) {
//                          検索結果を確認
                                response?.let {
                                    if(it.body().data != null) {
                                        if(it.body().status == "200"){
//                                      結果ありの場合
                                            if(it.body().data != null) {

//                                          画面更新
                                                updateFinalList()

                                                Toast.makeText(
                                                    requireContext(),
                                                    "休暇申込を承認した",
                                                    Toast.LENGTH_SHORT).show()
                                            }

                                        }

                                    }else {
                                        Toast.makeText(
                                            requireContext(),
                                            it.body().message,
                                            Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }

                            override fun onFailure(call: Call<BaseResponse<String>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                            }

                        })
                }

                dialog.dismiss()
            }

//          審査画面を表示
            alertDialog.show()
        }




//      画面更新
        fun updateList(){

//          画面の初期化
            binding.tableLayout1.removeAllViews()

            if(myAppAuthority == "2"){
//          審査待ちの休暇申込を検索
                RequestBuilder().getAPI(User::class.java).HolidayReview(HolidayReviewReq(myPersonalNo))
                    .enqueue(object : Callback<BaseResponse<List<HolidayAcquire>>> {
                        @SuppressLint("ResourceType", "SetTextI18n")
                        override fun onResponse(
                            call: Call<BaseResponse<List<HolidayAcquire>>>?,
                            response: Response<BaseResponse<List<HolidayAcquire>>>?
                        ) {
//                      検索結果を確認
                            response?.let {
                                if(it.body().data != null) {
                                    if(it.body().status == "200"){
//                                  検索結果ありの場合
                                        if(it.body().data != null) {
                                            it.body().data.let {

                                                it?.forEach {item ->

                                                    val row = TableRow(requireContext())
                                                    row.setPadding(0, 40, 0, 40)
//                                              現場をテーブルに加入
                                                    val member1 = TextView(requireContext())
                                                    member1.textSize = 20F
                                                    member1.width = 90
                                                    member1.text = item.selectedWorkSpot
                                                    row.addView(member1)
//                                              社員番号をテーブルに加入
                                                    val member2 = TextView(requireContext())
                                                    member2.textSize = 20F
                                                    member2.width = 90
                                                    member2.text = item.regAuthor
                                                    row.addView(member2)
//                                              開始時刻をテーブルに加入
                                                    val member3 = TextView(requireContext())
                                                    member3.textSize = 20F
                                                    member3.width = 90
                                                    member3.text = item.startYear.substring(2) + "/" + item.startMonth + "/" + item.startDay
                                                    row.addView(member3)
//                                              終了時刻をテーブルに加入
                                                    val member4 = TextView(requireContext())
                                                    member4.textSize = 20F
                                                    member4.width = 90
                                                    member4.text = item.endYear.substring(2) + "/" + item.endMonth + "/" + item.endDay
                                                    row.addView(member4)

//                                              検索結果のクリックで詳細画面を表示
                                                    member1.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
                                                    member2.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
                                                    member3.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
                                                    member4.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
//                                              テーブルを審査画面に追加
                                                    binding.tableLayout1.addView(row)

                                                }

                                            }
                                        }

                                    }

                                }else {
                                    Toast.makeText(
                                        requireContext(),
                                        it.body().message,
                                        Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<BaseResponse<List<HolidayAcquire>>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                        }

                    })
            }
            else if(myAppAuthority == "10"){
        //          審査待ちの休暇申込を検索
                RequestBuilder().getAPI(User::class.java).HolidayFinalReview()
                    .enqueue(object : Callback<BaseResponse<List<HolidayAcquire>>> {
                        @SuppressLint("ResourceType", "SetTextI18n")
                        override fun onResponse(
                            call: Call<BaseResponse<List<HolidayAcquire>>>?,
                            response: Response<BaseResponse<List<HolidayAcquire>>>?
                        ) {
        //                      検索結果を確認
                            response?.let {
                                if(it.body().data != null) {
                                    if(it.body().status == "200"){
        //                                  検索結果ありの場合
                                        if(it.body().data != null) {
                                            it.body().data.let {

                                                it?.forEach {item ->

                                                    val row = TableRow(requireContext())
                                                    row.setPadding(0, 40, 0, 40)
        //                                              現場をテーブルに加入
                                                    val member1 = TextView(requireContext())
                                                    member1.textSize = 20F
                                                    member1.width = 90
                                                    member1.text = item.selectedWorkSpot
                                                    row.addView(member1)
        //                                              社員番号をテーブルに加入
                                                    val member2 = TextView(requireContext())
                                                    member2.textSize = 20F
                                                    member2.width = 90
                                                    member2.text = item.regAuthor
                                                    row.addView(member2)
        //                                              開始時刻をテーブルに加入
                                                    val member3 = TextView(requireContext())
                                                    member3.textSize = 20F
                                                    member3.width = 90
                                                    member3.text = item.startYear.substring(2) + "/" + item.startMonth + "/" + item.startDay
                                                    row.addView(member3)
        //                                              終了時刻をテーブルに加入
                                                    val member4 = TextView(requireContext())
                                                    member4.textSize = 20F
                                                    member4.width = 90
                                                    member4.text = item.endYear.substring(2) + "/" + item.endMonth + "/" + item.endDay
                                                    row.addView(member4)

        //                                              検索結果のクリックで詳細画面を表示
                                                    member1.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
                                                    member2.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
                                                    member3.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
                                                    member4.setOnClickListener {
                                                        showAlertDialog(
                                                            item.selectedWorkSpot,
                                                            item.regAuthor,
                                                            item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                            item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                            vacationNoShow(item.vacationNo),
                                                            item.reason,
                                                            item.calendarNo,
                                                            item.refusal
                                                        )
                                                    }
        //                                              テーブルを審査画面に追加
                                                    binding.tableLayout1.addView(row)

                                                }

                                            }
                                        }

                                    }

                                }else {
                                    Toast.makeText(
                                        requireContext(),
                                        it.body().message,
                                        Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<BaseResponse<List<HolidayAcquire>>>?, t: Throwable?) {
        //                        Log.e("TAG","NetWorkErr!")
                        }

                    })
            }
        }
//
        binding.tableLayout1.let { it ->
//          テーブルの各列を画面幅に均等に分割
            it.isStretchAllColumns = true
//
            updateList();

        }

    }

    //  入力制限
    val Filter = InputFilter { source, _, _, _, _, _ ->
        val p = Pattern.compile(".+")
        val m = p.matcher(source.toString())
        if (!m.matches()) "" else null
    }

}