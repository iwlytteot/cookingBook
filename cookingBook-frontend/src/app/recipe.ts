import { Ingredient } from './ingredient';

export interface Recipe {
  id: number;
  name: string;
  description: string;
  portion: number;
  timeComplexity: { baseEstimate: string; additionalEstimate: string; }
  createTime: string;
  ingredients: [Ingredient, number];
}
