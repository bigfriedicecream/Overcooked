import React from 'react';

const Ingredient = ({ quantity, unitType, ingredientType, display }) => {
    return (
        <div className="d-flex">
            <div className="form-group">
                <label>Ingredient Type</label>
                <select className="form-control" value={ingredientType}>
                    <option value={0}>Normal</option>
                    <option value={1}>Heading</option>
                    <option value={2}>Text Only</option>
                </select>
            </div>
            <div className="form-group">
                <label>Quantity</label>
                <input type="number" className="form-control" value={quantity} />
            </div>
            <div className="form-group">
                <label>Unit Type</label>
                <select className="form-control" value={unitType}>
                    <option value={0}>Singular</option>
                    <option value={1}>Grams</option>
                    <option value={2}>Millilitres</option>
                    <option value={3}>Tbsp</option>
                    <option value={4}>tsp</option>
                    <option value={5}>Cups</option>
                    <option value={6}>Bunch</option>
                    <option value={7}>Rashers</option>
                    <option value={8}>Head</option>
                    <option value={9}>Sprig</option>
                    <option value={10}>Stalk</option>
                    <option value={11}>Sheets</option>
                    <option value={12}>Slice</option>
                </select>
            </div>
            <div className="form-group">
                <label>Display</label>
                <input type="text" className="form-control" value={display} />
            </div>
        </div>
    )
}

export default Ingredient;