package com.example.myrecipebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipebook.databinding.ActivityMainBinding
import com.example.myrecipebook.databinding.ActivityRecipeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recipeList: ArrayList<Recipe>
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recipeList = ArrayList<Recipe>()
        recipeAdapter = RecipeAdapter(recipeList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = recipeAdapter
        pullData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //inflater
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.recipe_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_recipe_item) {
            val intent = Intent(this@MainActivity, RecipeActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun pullData() {
        try {
            val database = this.openOrCreateDatabase("Recipes", MODE_PRIVATE, null)
            val cursor = database.rawQuery("SELECT * FROM recipes", null)
            val recipeNameIx = cursor.getColumnIndex("recipename")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()) {
                val name = cursor.getString(recipeNameIx)
                val id = cursor.getInt(idIx)
                val recipe = Recipe(name, id)
                recipeList.add(recipe)
            }
            recipeAdapter.notifyDataSetChanged()
            cursor.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}