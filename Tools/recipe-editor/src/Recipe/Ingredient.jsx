import React from 'react';
import { LookupIngType } from '../Lookups/LookupIngType'

const Ingredient = ({ ingredientType, quantity, ingredient, display }) => {
    return (
        <div className="d-flex">
            <div className="form-group">
                <label>Ingredient Type</label>
                <select className="form-control" value={ingredientType}>
                    {LookupIngType.dataList().map(item => {
                        const { id, description } = item
                        return (
                            <option key={`lookupingtype-${id}`} value={id}>{description}</option>
                        )
                    })}
                </select>
            </div>
            {
                ingredientType === LookupIngType.normal.id ? 
                    <div className="form-group">
                        <label>Quantity</label>
                        <input type="number" className="form-control" value={quantity} />
                    </div> : ''
            }
            {
                ingredientType === LookupIngType.heading.id || ingredientType === LookupIngType.textOnly.id ?
                    <div className="form-group">
                        <label>Display</label>
                        <input type="text" className="form-control" value={display} />
                    </div> : ''
            }
        </div>
    )
}

export default Ingredient;