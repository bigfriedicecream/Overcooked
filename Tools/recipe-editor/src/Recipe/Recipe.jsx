import React from 'react';
import Ingredient from './Ingredient'

const Recipe = ({ recipe, handlers }) => {
    const { id, title, serves, makes, prepTime, cookTime, ingredients } = recipe
    return (
        <div className="card">
            <div class="card-body">
                <h5 class="card-title">{title}</h5>
                <div className="form-group">
                    <label>Title</label>
                    <input type="text" className="form-control" value={title} onChange={handlers.onFieldChange(id, 'title')} />
                </div>
                <div className="d-flex">
                    <div className="form-group">
                        <label>Serves</label>
                        <input type="number" className="form-control" value={serves} onChange={handlers.onFieldChange(id, 'serves')} />
                    </div>
                    <div className="form-group">
                        <label>Makes</label>
                        <input type="number" className="form-control" value={makes} onChange={handlers.onFieldChange(id, 'makes')} />
                    </div>
                    <div className="form-group">
                        <label>Prep Time</label>
                        <input type="number" className="form-control" value={prepTime} onChange={handlers.onFieldChange(id, 'prepTime')} />
                    </div>
                    <div className="form-group">
                        <label>Cook Time</label>
                        <input type="number" className="form-control" value={cookTime} onChange={handlers.onFieldChange(id, 'cookTime')} />
                    </div>
                </div>
                <div>
                    <h6>Ingredients</h6>
                    {ingredients.map((ing, i) => {
                        return (
                            <Ingredient key={`recipe-${id}ing-${i}`} {...ing} />
                        )
                    })}
                </div>
                {/*<div>
                    Method
                </div>*/}
            </div>
        </div>
    )
}

export default Recipe;