'use strict';
	//window.alert('更新成功');

	var expanded = false;
        function showCheckboxes() {
            var checkboxes = document.getElementById("checkboxes");
            if (!expanded) {
                checkboxes.style.display = "block";
                expanded = true;
            } else {
                checkboxes.style.display = "none";
                expanded = false;
            }
        }
        function showCheckboxeses() {
            var checkboxes = document.getElementById("checkboxeses");
            if (!expanded) {
                checkboxes.style.display = "block";
                expanded = true;
            } else {
                checkboxes.style.display = "none";
                expanded = false;
            }
        }
        function showCheckboxeseses() {
            var checkboxes = document.getElementById("checkboxeseses");
            if (!expanded) {
                checkboxes.style.display = "block";
                expanded = true;
            } else {
                checkboxes.style.display = "none";
                expanded = false;
            }
        }
      function itemEdit(itemId) {
        //console.log(itemId);
        let form = document.createElement('form');
        form.action = '/coordinator//ItemRegistUpdateServlet';
        form.method = 'POST';
        document.body.append(form);
        form.addEventListener('formdata', (e) => {
          let fd = e.formData;
          // データをセット
          fd.set('item_id_edit', itemId);
        });
        form.submit();
      }