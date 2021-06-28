package com.example.menusinkotlin

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu

class SecondActivity : AppCompatActivity() {
    lateinit var listview:ListView
    var namelis = arrayListOf<String>("Albert","Newtor","Merry query","Elon mask","Dev","Aditi","Xyz","Albert","Newtor","Merry query","Elon mask","Dev","Aditi","Xyz")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        listview = findViewById(R.id.listview)

        var adapter :ArrayAdapter<String> = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_item,namelis)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        listview.adapter = adapter

 listview.setOnItemClickListener { parent, view, position, id ->
   //  Toast.makeText(applicationContext,"name => ${namelis[position]}",Toast.LENGTH_SHORT).show()
var popupMenu:PopupMenu = PopupMenu(this@SecondActivity,view)
     popupMenu.menuInflater.inflate(R.menu.popupmenufile,popupMenu.menu)

     popupMenu.setOnMenuItemClickListener { item ->
         var id = item.itemId
         if(id == R.id.popupone)
         {
             Toast.makeText(applicationContext,"onclck "+item.title,Toast.LENGTH_SHORT).show()
         }else if(id == R.id.popuptwo)
         {
             Toast.makeText(applicationContext,"onclck "+item.title,Toast.LENGTH_SHORT).show()
         }

         true
     }
     popupMenu.show()

 }

        registerForContextMenu(listview)

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)  {
        super.onCreateContextMenu(menu, v, menuInfo)

        var menuiflater : MenuInflater = menuInflater
        menuiflater.inflate(R.menu.contextmenu,menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        var id = item.itemId
        if(id == R.id.contextfirts)
        {
          var result =  sum(23,45)
            Toast.makeText(applicationContext," sum is => $result",Toast.LENGTH_SHORT).show()

        }else if(id == R.id.contextsecond)
        {
           var result : Int = multi(34,56)
            Toast.makeText(applicationContext," multi is => $result ",Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun multi(i: Int, i1: Int): Int {
      return i*i1
    }


    private fun sum(i: Int, i1: Int) : Int {
              return  i+i1
    }

}