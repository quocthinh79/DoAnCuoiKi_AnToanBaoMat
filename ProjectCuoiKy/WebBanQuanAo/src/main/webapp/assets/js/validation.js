// Đối tượng validator
function Validator(options) {
    function getParent(element, selector) {
        while (element.parentElement) {
            if (element.parentElement.matches(selector)) {
                return element.parentElement;
            }
            element = element.parentElement;
        }
    }

    // lấy element của form cần validate
    const formElement = document.querySelector(options.form);
    const selectorRules = {};
    if (formElement) {
        // xử lý submit
        formElement.onsubmit = (e) => {
            e.preventDefault();
            let isFormValid = true;
            // lặp qua từng rule và validate
            options.rules.forEach((rule) => {
                const inputElement = formElement.querySelector(rule.selector);
                const isValid = Validate(inputElement, rule);
                if (!isValid) {
                    isFormValid = false;
                }
            });
            if (isFormValid) {
                // Submit với javaScript
                if (typeof options.onSubmit === "function") {
                    const enableInputs = formElement.querySelectorAll(
                        "[name]:not([disabled])"
                    );
                    const formValues = Array.from(enableInputs).reduce(
                        (values, input) => {
                            switch (input.type) {
                                case "radio":
                                    values[input.name] = formElement.querySelector(
                                        'input[name="' + input.name + '"]:checked'
                                    ).value;
                                    break;
                                case "checkbox":
                                    if (!input.matches(":checked")) return values;
                                    if (!Array.isArray(values[input.name])) {
                                        values[input.name] = [];
                                    }
                                    values[input.name].push(input.value);
                                    break;
                                case "file":
                                    values[input.name] = input.files;
                                    break;
                                default:
                                    values[input.name] = input.value;
                            }
                            return values;
                        },
                        {}
                    );
                    options.onSubmit(formValues);
                }
                //Submit mặc định
                else {
                    formElement.submit();
                }
            }
        };
        // xử lý lặp qua mỗi rule và xử lý (lắng nghe blur, input...)
        options.rules.forEach((rule) => {
            // Lưu lại các rules cho mỗi input
            if (Array.isArray(selectorRules[rule.selector])) {
                selectorRules[rule.selector].push(rule.test);
            } else {
                selectorRules[rule.selector] = [rule.test];
            }
            const inputElements = formElement.querySelectorAll(rule.selector);
            Array.from(inputElements).forEach((inputElement) => {
                const parentElement = getParent(
                    inputElement,
                    options.formGroupSelector
                );
                if (inputElement) {
                    // xử lý blur khỏi input
                    inputElement.onblur = () => {
                        Validate(inputElement, rule);
                    };
                    // xử lý mỗi khi người dùng nhập vào input
                    inputElement.oninput = () => {
                        const errorElement = parentElement.querySelector(
                            options.errorSelector
                        );
                        errorElement.innerText = "";
                        parentElement.classList.remove("invalid");
                    };
                }
            });
        });

        // hàm thực hiện validate
        function Validate(inputElement, rule) {
            const parentElement = getParent(inputElement, options.formGroupSelector);
            const errorElement = parentElement.querySelector(options.errorSelector);
            let errorMessage;
            // lấy ra các rules của selector
            const rules = selectorRules[rule.selector];
            // lập qua từng rule và check
            for (let i = 0; i < rules.length; i++) {
                switch (inputElement.type) {
                    case "checkbox":
                    case "radio":
                        errorMessage = rules[i](
                            formElement.querySelector(rule.selector + ":checked")
                        );
                        break;
                    default:
                        errorMessage = rules[i](inputElement.value);
                }
                if (errorMessage) break;
            }

            if (errorMessage) {
                parentElement.classList.add("invalid");
                errorElement.innerText = errorMessage;
            } else {
                errorElement.innerText = "";
                parentElement.classList.remove("invalid");
            }
            return !errorMessage;
        }
    }
}

// định nghĩa các rules
// Nguyên tắc của các rule
// 1. Khi có lỗi thì trả ra message lỗi
// 2. Khi không lỗi trả về undefined
Validator.isRequire = (selector, message) => {
    return {
        selector,
        test(value) {
            return value ? undefined : message || "Vui lòng nhập trường này!";
        },
    };
};
Validator.isEmail = (selector, message) => {
    return {
        selector,
        test(value) {
            const regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value)
                ? undefined
                : message || "Trường này phải là email!";
        },
    };
};
Validator.isPhone = (selector, message) => {
    return {
        selector,
        test(value) {
            const regex = /^[\s()+-]*([0-9][\s()+-]*){10,12}$/
            return regex.test(value)
                ? undefined
                : message || "Trường này phải là số điện thoại!";
        },
    };
};
Validator.isUsername = (selector, message) => {
    return {
        selector,
        test(value) {
            const regex = /^[a-zA-Z0-9]{4,20}$/;
            return regex.test(value)
                ? undefined
                : message || "Trường này phải là tài khoản!";
        },
    };
};
Validator.isPassword = (selector, message) => {
    return {
        selector,
        test(value) {
            const regex = /^(?=.*\d).{4,}$/;
            return regex.test(value)
                ? undefined
                : message || "Trường này phải là mật khẩu!";
        },
    };
};
Validator.minLength = (selector, min, message) => {
    return {
        selector,
        test(value) {
            return value.length >= min
                ? undefined
                : message || `Vui lòng nhập tối thiểu ${min} kí tự!`;
        },
    };
};
Validator.isConfirmed = (selector, getConfirmValue, message) => {
    return {
        selector,
        test(value) {
            return value === getConfirmValue()
                ? undefined
                : message || "Giá trị nhập vào không chính xác";
        },
    };
};
Validator.isNumber = (selector, message) => {
    return {
        selector,
        test(value) {
            const regex = /^\d+$/
            return regex.test(value)
                ? undefined
                : message || "Giá trị phải là số";
        },
    };
};


/////////////////////////////////////
Validator({
    form: "#register_form",
    errorSelector: ".form_message",
    formGroupSelector: ".form-group",
    rules: [
        Validator.isRequire("#last-name", "Vui lòng nhập họ"),
        Validator.isRequire("#first-name", "Vui lòng nhập tên"),
        Validator.isRequire('#email', "Vui lòng nhập email"),
        Validator.isRequire('#phone', "Vui lòng nhập số điện thoại"),
        Validator.isRequire('#username', "Vui lòng nhập tên tài khoản"),
        Validator.isRequire('#password', "Vui lòng nhập mật khẩu"),
        Validator.isRequire('#password-repeat', "Vui lòng nhập lại mật khẩu"),
        Validator.isEmail("#email", "Email không hợp lệ"),
        Validator.isPhone("#phone", "Số điện thoại không hợp lệ"),
        Validator.isUsername("#username", "Tài khoản phải từ 4-20 ký tự và không chứa ký tự đặc biệt"),
        Validator.isPassword("#password", "Mật khẩu ít nhất là 4 ký tự và không chứa ký tự đặc biệt"),
        Validator.isConfirmed(
            "#password-repeat",
            () => {
                return document.querySelector("#register_form #password").value;
            },
            "Mật khẩu nhập lại không chính xác"
        ),
    ],
});
Validator({
    form: "#login-form",
    errorSelector: ".form-error",
    formGroupSelector: ".form-group",
    rules: [
        Validator.isRequire('#username', "Vui lòng nhập tên tài khoản"),
        Validator.isRequire('#password', "Vui lòng nhập mật khẩu"),
        Validator.isUsername("#username", "Tài khoản phải từ 4-20 ký tự và không chứa ký tự đặc biệt"),
        Validator.isPassword("#password", "Mật khẩu ít nhất là 4 ký tự và không chứa ký tự đặc biệt"),
    ],
});
Validator({
    form: "#form-add-user",
    errorSelector: ".form-error",
    formGroupSelector: ".form-group",
    rules: [
        Validator.isRequire("#last_name", "Vui lòng nhập họ"),
        Validator.isRequire("#first_name", "Vui lòng nhập tên"),
        Validator.isRequire('#email', "Vui lòng nhập email"),
        Validator.isRequire('#phone', "Vui lòng nhập số điện thoại"),
        Validator.isRequire('#username', "Vui lòng nhập tên tài khoản"),
        Validator.isRequire('#password', "Vui lòng nhập mật khẩu"),
        Validator.isRequire('#repeat_password', "Vui lòng nhập lại mật khẩu"),
        Validator.isEmail("#email", "Email không hợp lệ"),
        Validator.isPhone("#phone", "Số điện thoại không hợp lệ"),
        Validator.isUsername("#username", "Tài khoản phải từ 4-20 ký tự và không chứa ký tự đặc biệt"),
        Validator.isPassword("#password", "Mật khẩu ít nhất là 4 ký tự và không chứa ký tự đặc biệt"),
        Validator.isConfirmed(
            "#repeat_password",
            () => {
                return document.querySelector("#form-add-user #password").value;
            },
            "Mật khẩu nhập lại không chính xác"
        ),
    ],
});
Validator({
    form: "#form-add-product",
    errorSelector: ".form-error",
    formGroupSelector: ".form-group",
    rules: [
        Validator.isRequire("#code-product", "Vui lòng nhập mã sản phẩm"),
        Validator.isRequire("#name-product", "Vui lòng nhập tên sản phẩm"),
        Validator.isRequire("#brand", "Vui lòng nhập tên hãng"),
        // Validator.isRequire('#type-product', "Vui lòng nhập loại sản phẩm"),
        Validator.isRequire('#price-product', "Vui lòng nhập giá sản phẩm"),
        Validator.isRequire('#description', "Vui lòng nhập mô tả sản phẩm"),
        Validator.isRequire('#thumbnail', "Vui lòng chọn hình đại diện sản phẩm"),
        // Validator.isRequire('#images', "Vui lòng chọn hình chi tiết sản phẩm"),
        Validator.isRequire('#tag-product', "Vui lòng chọn thẻ sản phẩm"),
        Validator.isRequire('#color-product', "Vui lòng chọn màu sản phẩm"),
        Validator.isRequire('#size-product', "Vui lòng chọn kích thước sản phẩm"),
        Validator.isNumber("#price-product", "Gía không hợp lệ")
    ]
});
