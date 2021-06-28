function loadGroups() {
    $.ajax("/api/groups/all").done(function(data) {
        var i;
        for (i = 0; i < data.length; i++) {
            var group = data[i];
            var optionElement = document.createElement('option');
            optionElement.value = group.id;
            optionElement.textContent = group.groupName;
            $('select').append(optionElement);
        }
    });
}
