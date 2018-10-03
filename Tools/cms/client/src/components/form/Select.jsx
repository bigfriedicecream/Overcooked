import React from 'react';

const Select = ({ label, options, ...restProps }) => {
    return (
        <div className="form-group">
            <label>{label}</label>
            <select className="form-control" {...restProps}>
                {options.map(item => {
                    return (
                        <option key={`ingunittype-${item.id}`} value={item.id}>{item.description}</option>
                    )
                })}
            </select>
        </div>
    )
}

export default Select;