import React from 'react';

const Textarea = ({ ...props }) => {
    return (
        <div className="form-group">
            <textarea type="text" className="form-control" {...props} />
        </div>
    )
}

export default Textarea;