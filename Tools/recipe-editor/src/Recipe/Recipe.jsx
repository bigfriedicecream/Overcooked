import React from 'react';

const Recipe = ({ id, title, serves, makes, prepTime, cookTime, handlers }) => {
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
                    Ingredients
                    {/*recipe.ingredients.map((ing, i) => {
                        return (
                            <div key={`recipe-${key}ing-${i}`}>
                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input type="number" className="form-control" value="1" />
                                </div>
                                <div class="form-group">
                                    <label>Ingredient Type</label>
                                    <select className="form-control">
                                        <option>op1</option>
                                        <option>op2</option>
                                        <option>op3</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Recipe Display</label>
                                    <input type="text" className="form-control" value="Recipe Display" />
                                </div>
                            </div>

                        )
                    })*/}
                </div>
                {/*<div>
                    Method
                </div>*/}
            </div>
        </div>
    )
}

export default Recipe;