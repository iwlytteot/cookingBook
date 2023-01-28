import {Component, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DataViewModule} from 'primeng/dataview';
import {Recipe} from "../model/recipe";

@Injectable({
  providedIn: 'root',
})
@Component({
  selector: 'app-recipe-service',
  templateUrl: './recipe-service.component.html',
  styleUrls: ['./recipe-service.component.css']
})
export class RecipeServiceComponent {

  constructor(private http: HttpClient) {}

  recipes = {} as Recipe[];

  getRecipes() {
    return this.http.get<Recipe[]>('http://localhost:8080/recipe');
  }

}
