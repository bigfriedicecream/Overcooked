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
                    <Select label="Unit Type" value={ingredient.ingUnitTypeId} onChange={handlers.onIngredientFieldChange(ingredient.id, 'ingUnitTypeId')} options={LookupIngUnitType.dataList()} />
                </div>
            </div>
            <div className="row">
                <div className="col-xl">
                    <div className="card">
                        <div className="card-body">
                            <h5 className="card-title">Alternate Units</h5>
                            {ingredient.alternateUnits.map((unit, i) => {
                                return (
                                    <div key={`alternateunit-${i}`} className="d-flex">
                                        <Select label="Unit Type" value={unit.unitTypeId} onChange={() => { }} options={LookupIngUnitType.dataList()} />
                                        <Number label="Multiplier" value={unit.multiplier} onChange={() => { }} />
                                        <button type="button" className="btn btn-link text-danger float-right" onClick={() => { }}>Remove</button>
                                    </div>
                                )
                            })}
                            <br />
                            <button type="button" className="btn btn-primary" onClick={handlers.onAddAlternateUnit(ingredient.id)}>+</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default withRouter(Ingredient);