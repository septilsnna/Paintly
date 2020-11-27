package com.mobcom.paintly

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.view.*
import org.jetbrains.anko.support.v4.runOnUiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(){
    lateinit var mView: View
    lateinit var quota: String

    val SHARED_PREFS = "sharedPrefs"
    val QUOTA = "quota"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.activity_home, container, false)

        val sharedPreferences = activity?.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        quota = sharedPreferences?.getInt(QUOTA, 0)!!.toInt().toString()
//        mView.quota_today.text = mView.quota_today.text.toString() + quota

        RetrofitClient.instance.getStyles(
        ).enqueue(object : Callback<List<StyleData>> {
            override fun onFailure(call: Call<List<StyleData>>, t: Throwable) {
                mView.failed_styles.visibility = View.VISIBLE
//                Toast.makeText(activity, "Failed to load styles, please check your connection.", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<StyleData>>, response: Response<List<StyleData>>) {
                if (response.code() == 200) {
                    loadingStyles(response.body()!!)
                } else {
                }
            }
        })

        return mView
    }

    override fun onResume() {
        super.onResume()
        // Set title bar
        (activity as BottomNavActivity)
            .setActionBarTitle("Pick Your Style")
//            .setActionBarTitle("Pick Your Style (quota/day: $quota)")
    }

    private fun loadingStyles(styleData: List<StyleData>) {
        mView.progress_bar_style.visibility = View.VISIBLE
        Thread {
            val styleAdapter = StyleAdapter( // deklarasi styleAdapter yang bentuknya StyleAdapter()
                this.requireContext(),
                styleData,
                object : StyleAdapter.ClickListener {
                    override fun onClick(styleId: String?) {
                        val arguments = Bundle()
                        arguments.putString("styleId", styleId)
                        val fragment = UploadSheetFragment()
                        fragment.arguments = arguments
                        fragment.show(childFragmentManager, "UploadSheetDialog")
                    }
                }
            )
            runOnUiThread {
                mView.rv_style.adapter =
                    styleAdapter // adapter dari rv nya di set jadi styleAdapter (dideclare di atas)
                mView.rv_style.layoutManager = LinearLayoutManager(context)
                mView.progress_bar_style.visibility = View.GONE
            }
        }.start() // mulai ehehehehehe
    }

}


