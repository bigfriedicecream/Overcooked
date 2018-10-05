export const LookupIngDisplayType = {
    normal: { id: 0, description: "Normal" },
    heading: { id: 1, description: "Heading" },
    textOnly: { id: 2, description: "Text Only" },

    dataLookup(id) {
        if (id === LookupIngDisplayType.normal.id) { return LookupIngDisplayType.normal; }
        if (id === LookupIngDisplayType.heading.id) { return LookupIngDisplayType.heading; }
        if (id === LookupIngDisplayType.textOnly.id) { return LookupIngDisplayType.textOnly; }
        return {}
    },

    dataList() {
        return [
            LookupIngDisplayType.normal,
            LookupIngDisplayType.heading,
            LookupIngDisplayType.textOnly
        ]
    }
}