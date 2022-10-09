import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ingredient } from '../ingredient';
import { Recipe } from '../recipe';

@Component({
  selector: 'app-get-data',
  templateUrl: './get-data.component.html',
  styleUrls: ['./get-data.component.css']
})
export class GetDataComponent {

  constructor(private http: HttpClient) {}

  ingredients = {} as Ingredient[];
  recipes = {} as Recipe[];

  onGetIngredients() {
    this.http.get<Ingredient[]>('http://localhost:8080/ingredient').subscribe((data) => {
      this.ingredients = data;
    });
  }

  onGetRecipes() {
    this.http.get<Recipe[]>('http://localhost:8080/recipe').subscribe((data) => {
      this.recipes = data;
    });
  }
}
