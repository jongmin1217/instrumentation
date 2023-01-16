package com.bellminp.instrumentation.ui.usinginfo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bellminp.instrumentation.R
import com.bellminp.instrumentation.databinding.ActivityUsingInfoBinding
import com.bellminp.instrumentation.model.UsingInfoData
import com.bellminp.instrumentation.ui.base.BaseActivity
import com.bellminp.instrumentation.ui.splash.SplashViewModel

class UsingInfoActivity :
    BaseActivity<ActivityUsingInfoBinding, UsingInfoViewModel>(R.layout.activity_using_info) {

    override val viewModel: UsingInfoViewModel by viewModels()

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()

        binding.tvBack.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {
        binding.rvUsingInfo.adapter = UsingInfoAdapter().apply {
            submitList(
                arrayListOf(
                    UsingInfoData(
                        "트리사용",
                        "트리사용은 아래 이미지와 같이 해당 그룹계측기 또는 계측기를 선택후 더블탭을 하면 바로 그래프를 볼수 있습니다.",
                        R.drawable.smart_info
                    ),
                    UsingInfoData(
                        "표 보기",
                        "표 보기는 트리에서 그룹계측기 또는 계측기를 선택한 후 표 보기 아이콘을 선택하면 해당 결과를 볼수 있습니다. 이때 검색 기간은 기본으로 최종데이터 에서 3일전 데이터 이며, 사용자가 원하는 기간은 위에 검색 기간을 설정 하시면 됩니다.",
                        R.drawable.smartphone_login
                    ),
                    UsingInfoData(
                        "그래프 보기",
                        "그래프 보기는 트리에서 그룹계측기 또는 계측기를 선택한 후 그래프 보기 아이콘을 선택하면 해당 결과를 볼수 있습니다. 이때 검색 기간은 기본으로 최종데이터 에서 3일전 데이터 이며, 사용자가 원하는 기간은 위에 검색 기간을 설정 하시면 됩니다.",
                        R.drawable.smartphone_login
                    )
                )
            )
        }


    }
}