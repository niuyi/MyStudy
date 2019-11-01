package com.niu.mystudy

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Environment
import android.util.Log
import java.io.File
import java.util.*

object DataLoader{

    private const val TAG: String = "TEST"

    lateinit var categoryItems : ArrayList<CategoryItem>

    fun loadData() {
        Log.i(TAG, "running from lambda: ${Thread.currentThread()}")

        val externalStorageDirectory = Environment.getExternalStorageDirectory()
        val root = File(externalStorageDirectory, "qtstudy")
        val dictDir = File(root, "dict")
        Log.i("TEST", "path： " + dictDir.absolutePath)

        categoryItems = ArrayList<CategoryItem>()

        for (dir in dictDir.listFiles()!!) {
            val item = CategoryItem(dir.name, File(dir, "cover.jpg").absolutePath)
            categoryItems.add(item)
        }

        categoryItems.sortWith(Comparator { (left), (right) ->

            val array1 = left.split(" ")
            val v1 = array1[array1.size - 1]

            val array2 = right.split(" ")
            val v2 = array2[array2.size - 1]

            Integer.valueOf(v1).compareTo(Integer.valueOf(v2))
        })
    }



}