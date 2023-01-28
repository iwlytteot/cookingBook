import { Component, OnInit } from '@angular/core';
import {RecipeServiceComponent} from "../recipe-service/recipe-service.component";
import {Recipe} from "../model/recipe";

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  constructor(private recipeService: RecipeServiceComponent) { }

  recipes = {} as Recipe[];

  ngOnInit(): void {
    this.recipeService.getRecipes().subscribe((data) => {
      this.recipes = data;
    });
  }
}
