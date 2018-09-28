import React from 'react';
import { LookupIngType } from '../Lookups/LookupIngType'

const Ingredient = ({ ingType, quantity, ingredient, display, ingredients }) => {
    return (
        <div className="d-flex">
            <div className="form-group">
                <label>Ingredient Type</label>
                <select className="form-control" value={ingType} readOnly>
                    {LookupIngType.dataList().map(item => {
                        const { id, description } = item
                        return (
                            <option key={`lookupingtype-${id}`} value={id}>{description}</option>
                        )
                    })}
                </select>
            </div>
            {
                ingType === LookupIngType.normal.id ? 
                    <div className="form-group">
                        <label>Quantity</label>
                        <input type="number" className="form-control" value={quantity} readOnly />
                    </div> : ''
            }
            {
                ingType === LookupIngType.heading.id || ingType === LookupIngType.textOnly.id ?
                    <div className="form-group">
                        <label>Display</label>
                        <input type="text" className="form-control" value={display} readOnly />
                    </div> : ''
            }
            {
                ingType === LookupIngType.normal.id ?
                    <div className="form-group">
                        <label>Ingredient</label>
                        <select className="form-control" value={ingredient} readOnly>
                            {Object.keys(ingredients).map(key => {
                                const { id, name, namePlural } = ingredients[key];
                                const displayName = quantity <= 1 ? name : namePlural;
                                return (
                                    <option key={`ingredient-${id}`} value={id}>{displayName}</option>
                                )
                            })}
                        </select>
                    </div> : ''
            }
        </div>
    )
}

export default Ingredient;