import React from 'react';
import { withRouter } from "react-router";
import { LookupIngUnitType } from '../../lookups/LookupIngUnitType';
import Text from '../form/Text';
import Select from '../form/Select';
import Number from '../form/Number';

const Ingredient = ({ data, handlers, match }) => {
    if (data === null) return null

    const ingredient = data.ingredients[match.params.id];
    
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-xl">
                    <h2>{ingredient.name}</h2>
                    <p><small>ID: {ingredient.id}</small></p>
                    <Text label="Name" value={ingredient.name} onChange={handlers.onIngredientFieldChange(ingredient.id, 'name')} />
                    <Text label="Name Plural" value={ingredient.namePlural} onChange={handlers.onIngredientFieldChange(ingredient.id, 'namePlural')} />
                </div>
            </div>
            <div className="row">
                <div className="col-xl">
                    <div className="card">
                        <div className="card-body">
                            <h5 className="card-title">Units</h5>
                            {ingredient.unitTypes.map((unit, i) => {
                                return (
                                    <div key={`alternateunit-${i}`} className="d-flex">
                                        <Select label="Unit Type" value={unit.id} onChange={handlers.onIngUnitFieldChange(ingredient.id, i, 'id')} options={LookupIngUnitType.dataList()} />
                                        <Number label="Ratio" value={unit.ratio} onChange={handlers.onIngUnitFieldChange(ingredient.id, i, 'ratio')} />
                                        <button type="button" className="btn btn-link text-danger float-right" onClick={handlers.onRemoveIngredientUnit(ingredient.id, i)}>Remove</button>
                                    </div>
                                )
                            })}
                            <br />
                            <button type="button" className="btn btn-primary" onClick={handlers.onAddIngredientUnit(ingredient.id)}>+</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default withRouter(Ingredient);