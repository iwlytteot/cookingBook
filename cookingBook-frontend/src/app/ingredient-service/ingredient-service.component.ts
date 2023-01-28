import {Component, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Ingredient} from "../model/ingredient";

@Injectable({
  providedIn: 'root',
})
@Component({
  selector: 'app-ingredient-service',
  templateUrl: './ingredient-service.component.html',
  styleUrls: ['./ingredient-service.component.css']
})
export class IngredientServiceComponent {

  constructor(private http: HttpClient) {}

  ingredients = {} as Ingredient[];

  getIngredients() {
    this.http.get<Ingredient[]>('http://localhost:8080/ingredient').subscribe((data) => {
      this.ingredients = data;
    });
  }
}
