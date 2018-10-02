import React from 'react';

const RecipeList = ({ data, handlers }) => {
    if (data === null) return null

    const { recipes } = data

    return (
        <div>
            {Object.keys(recipes).map(key => {
                const recipe = recipes[key];
                // console.log(recipe);
                return (
                    <div key={`recipe-${recipe.id}`}>
                        <h2>{recipe.title}</h2>
                        <div className="form-group">
                            <label>Title</label>
                            <input type="text" className="form-control" value={recipe.title} onChange={handlers.onRecipeFieldChange(recipe.id, 'title')} />
                        </div>
                        <div className="d-flex">
                            <div className="form-group">
                                <label>Serves</label>
                                <input type="number" className="form-control" value={recipe.serves} onChange={handlers.onRecipeFieldChange(recipe.id, 'serves')} />
                            </div>
                            <div className="form-group">
                                <label>Makes</label>
                                <input type="number" className="form-control" value={recipe.makes} onChange={handlers.onRecipeFieldChange(recipe.id, 'makes')} />
                            </div>
                            <div className="form-group">
                                <label>Prep Time</label>
                                <input type="number" className="form-control" value={recipe.prepTime} onChange={handlers.onRecipeFieldChange(recipe.id, 'prepTime')} />
                            </div>
                            <div className="form-group">
                                <label>Cook Time</label>
                                <input type="number" className="form-control" value={recipe.cookTime} onChange={handlers.onRecipeFieldChange(recipe.id, 'cookTime')} />
                            </div>
                        </div>
                        <div>
                            <h6>Ingredients</h6>
                        </div>
                    </div>
                )
            })}
        </div>
    )
}

export default RecipeList