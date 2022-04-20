package com.onwd.challenge.ListAdapter

import android.media.Image
import com.onwd.devices.IDevice
import java.io.Serializable


public data class ItemsViewModel(val image: Int,val item: IDevice) : Serializable{
}