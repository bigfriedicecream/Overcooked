import React from 'react';
import { withRouter } from "react-router";

const Recipe = ({ data, handlers, match }) => {
    if (data === null) return null

    const recipe = data.recipes[match.params.id];
    
    return (
        <div>
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
}

export default withRouter(Recipe);