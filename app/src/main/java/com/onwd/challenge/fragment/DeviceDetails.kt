package com.onwd.challenge.fragment
import com.onwd.challenge.ListAdapter.ItemsViewModel
import androidx.fragment.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.onwd.challenge.R
import android.view.Gravity

import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.os.Build
import androidx.core.content.ContextCompat
import android.graphics.drawable.ColorDrawable


class DeviceDetails : DialogFragment() {

    companion object {
        const val TAG = "Fragment"
        private const val KEY_ITEM = "KEY_ITEM"

        fun newInstance(item: ItemsViewModel): DeviceDetails {
            val args = Bundle()
            args.putSerializable(KEY_ITEM, item)
            val fragment = DeviceDetails()
            fragment.arguments = args
            return fragment
        }

    }
    private var deviceItem : ItemsViewModel?=null
    lateinit var close_button : View
    lateinit var  devicename : TextView
    lateinit var  firmversion : TextView
    lateinit var  device_status : TextView
    lateinit var  device_battery_level : TextView
    lateinit var  device_image : ImageView
    lateinit var  toolbar : Toolbar


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_devicedetails, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        BindData(view);
    }

    override fun onStart() {
        super.onStart()
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialogStyle);

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireContext(),android.R.color.transparent)))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            dialog?.window?.statusBarColor = ContextCompat.getColor(requireContext(),R.color.red)
            dialog?.window?.decorView?.systemUiVisibility =
                SYSTEM_UI_FLAG_LAYOUT_STABLE //solves issue with statusbar
            dialog?.window?.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.TOP)
        }
    }


    private fun setupView(view: View) {
        deviceItem =  arguments!!.getSerializable(KEY_ITEM) as? ItemsViewModel
        close_button = view.findViewById<MaterialButton>(R.id.close_button)
        devicename = view.findViewById<TextView>(R.id.devicename)
        firmversion = view.findViewById<TextView>(R.id.firmversion)
        device_status = view.findViewById<TextView>(R.id.device_status)
        device_battery_level = view.findViewById<TextView>(R.id.device_battery_level)
        device_image = view.findViewById<ImageView>(R.id.device_image_center)
        toolbar = view.findViewById<Toolbar>(R.id.toolbar);

    }
    private fun BindData(view: View) {

        toolbar.setNavigationOnClickListener{
            dismiss()
        }

        devicename.text = deviceItem!!.item.name
        toolbar.setTitle(deviceItem!!.item.name)
        firmversion.text = deviceItem!!.item.firmwareVersion
        device_battery_level.text = String.format("%.1f", deviceItem!!.item.batteryLevel).toString()+ " %"
        device_image!!.setImageResource(deviceItem!!.image)

        close_button.setOnClickListener {
            dismiss()
        }
    }
}