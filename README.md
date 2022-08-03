# Recipe Management Service

## Motivation and abstract

The **Recipe Management Service** exposes an endpoints related to the recipe, offering multiple operations:
  1. `/ingredients`. To get the details of all ingredients in alphabetical order .
  2. `/recipesfilters`. To get the recipe details by receipeType ,ingredients ,servings,instrutions or any parameter.
  3. `/recipes`. To get all recipe details.
  4. `/recipes/{name}`. To get the recipe details by recipe name.
  5. `/recipes/create`. To create the recipe.
  6. `/recipes/{name}`. To delete the recipe details by recipe name.
  7. `/recipes/delete`. To delete the all recipe details.
  8. `/recipes/{name}`. To update recipe details by recipe name.

**Database**. The H2 database from which this service retrieve the business information that delivers.
 The related tables are:
  - `recipe`. Maintain information of recipe.
  - `ingredients`. Maintain information of ingredients.
  - `recipe_ingredients`. Maintain information about which are the ingredients used in recipe.
  

#### Endpoints


 Swagger-ui : http://localhost:8080//swagger-ui.html#/

- GET::/api/ingredients : Retrieve the details of all ingredients in alphabetical order.
- GET::/api/recipesfilters : Retrieve the recipe details by receipeType ,ingredients ,servings,instrutions or any parameter.
- GET::/api/recipes : Retrieve the details of all recipe.		
- GET::/api/recipes/{name} : Retrieve the details of recipe by name.		
- POST::/api/recipes/create: To create the recipe.
- DELETE::/api/recipes/delete : To delete the all recipe details.
- DELETE::/api/recipes/{name} : To delete the recipe details by recipe name.
- PUT::/api/recipes/{name} : To update recipe details by recipe name.
