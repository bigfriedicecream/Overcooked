import React from 'react';
import { withRouter } from "react-router";
import { LookupIngUnitType } from '../../lookups/LookupIngUnitType';

const Ingredient = ({ data, handlers, match }) => {
    if (data === null) return null

    const ingredient = data.ingredients[match.params.id];
    return (
        <div>
            <div className="form-group">
                <label>Name</label>
                <input type="text" className="form-control" value={ingredient.name} onChange={handlers.onIngredientFieldChange(ingredient.id, 'name')} />
            </div>
            <div className="form-group">
                <label>Name Plural</label>
                <input type="text" className="form-control" value={ingredient.namePlural} onChange={handlers.onIngredientFieldChange(ingredient.id, 'namePlural')} />
            </div>
            <div className="form-group">
                <label>Unit Type</label>
                <select className="form-control" value={ingredient.ingUnitType} onChange={handlers.onIngredientFieldChange(ingredient.id, 'ingUnitType')}>
                    {LookupIngUnitType.dataList().map(item => {
                        return (
                            <option key={`ingunittype-${item.id}`} value={item.id}>{item.description}</option>
                        )
                    })}
                </select>
            </div>
        </div>
    )
}

export default withRouter(Ingredient);