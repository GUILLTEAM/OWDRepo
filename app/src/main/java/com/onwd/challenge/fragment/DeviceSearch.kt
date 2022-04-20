package com.onwd.challenge.fragment;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onwd.devices.DeviceInteractorStub
import com.onwd.devices.IDevice
import com.onwd.devices.IDeviceFoundListener
import com.google.android.material.button.MaterialButton
import androidx.recyclerview.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.onwd.challenge.ListAdapter.CenterDecoration
import com.onwd.challenge.ListAdapter.CenterSnapHelper
import com.onwd.challenge.ListAdapter.ItemsViewModel
import com.onwd.challenge.ListAdapter.CustomAdapter
import kotlinx.coroutines.*
import com.onwd.challenge.CellClickListener
import com.onwd.challenge.MainActivity
import com.onwd.challenge.R
import android.util.Log


public class DeviceSearch: Fragment(), CellClickListener {
//Fixed issue
    //test
    private val deviceInteractor: DeviceInteractorStub?=null
    private var selectedDeviceItem : ItemsViewModel?=null
    lateinit var button_search : View
    lateinit var button_open : View
    lateinit var device_found : TextView
    lateinit var recyclerView : RecyclerView

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val snapHelper = CenterSnapHelper()
    private var mDeviceItem = ArrayList<ItemsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View?=null;
        view =  inflater.inflate(R.layout.fragment_devicesearch,container,false)

        setupView(view)

        recyclerView.addItemDecoration(CenterDecoration(0))
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayout.HORIZONTAL, false)
        var adapter = CustomAdapter(mDeviceItem,snapHelper,this)
        recyclerView.adapter = adapter

        val deviceInteractor=DeviceInteractorStub()
        deviceInteractor.registerListener(object : IDeviceFoundListener{
            override fun deviceFound(device: IDevice) {
                GlobalScope.launch {
                    withContext(Dispatchers.IO) {

                        when (device.getType()) {
                            0 -> mDeviceItem.add(
                                ItemsViewModel(
                                    R.drawable.ic_baseline_watch,
                                    device
                                )
                            )
                            1 -> mDeviceItem.add(
                                ItemsViewModel(
                                    R.drawable.ic_baseline_smartphone,
                                    device
                                )
                            )
                            else -> { // Note the block
                                mDeviceItem.add(
                                    ItemsViewModel(
                                        R.drawable.ic_baseline_desktop_mac,
                                        device
                                    )
                                )
                            }
                        }
                        withContext(Dispatchers.Main) {
                            adapter.refresAdapter(mDeviceItem)
                            device_found.text= "Found " + adapter.getItemCount().toString()+" Device"
                        }
                    }
                }
            }
        })


        button_search?.setOnClickListener {
            deviceInteractor?.startSearch();
        }

        button_open?.setOnClickListener {
            val item=selectedDeviceItem;
            if (item != null) {
                DeviceDetails.newInstance(selectedDeviceItem!!)
                    .show(requireActivity().supportFragmentManager, DeviceDetails.TAG)
            } else {
                Toast.makeText(requireContext(), R.string.desc_toast, Toast.LENGTH_LONG).show();
            }
        }
        return view;
    }


    override fun onCellClickListener(items: ItemsViewModel ) {
        selectedDeviceItem=items;
    }


    private fun setupView(view: View) {
        button_search = view.findViewById<MaterialButton>(R.id.button_search);
        button_open = view.findViewById<MaterialButton>(R.id.button_open);
        device_found = view.findViewById<TextView>(R.id.device_found);
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
    }
}